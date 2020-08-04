package com.bla.imagefetch.upper.service.task;

import com.aliyuncs.exceptions.ClientException;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.common.util.SpringUtil;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;
import com.bla.imagefetch.upper.service.VO.VOConvertToDO;
import com.bla.imagefetch.upper.service.topAlg.aliyun.ImageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 图片任务实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 20:52
 */
@Component(value = "imageTask")
public class ImageTaskImpl implements AbstractTask, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageTaskImpl.class);

    @Autowired
    private GlobalConstant globalConstant;

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Override
    public Integer addServiceConfig(ServiceConfigVO serviceConfigVO) throws RuntimeException {
        Integer ret = serviceConfigRepository.insert(VOConvertToDO.VOServiceConfigDO(serviceConfigVO));
        if (ret == null){
            throw new RuntimeException("serviceConfigRepository.insert != 1");
        }else{
            return ret;
        }
    }

    @Override
    public Integer addTaskConfig(TaskConfigVO taskConfigVO) throws RuntimeException {
        Integer ret = taskConfigRepository.insert(VOConvertToDO.VOTaskConfigDO(taskConfigVO));
        if (ret == null){
            throw new RuntimeException("taskConfigRepository.insert != 1");
        }else{
            return ret;
        }
    }

    @Override
    public void addTasks(TaskInstanceAndDetailVO taskInstanceAndDetailVO) throws RuntimeException {
        String serviceConfigName = taskInstanceAndDetailVO.getServiceName();
        String taskConfigName = taskInstanceAndDetailVO.getConfigName();
        String directory = taskInstanceAndDetailVO.getDirectory();

        TaskConfigDO taskConfigDO = taskConfigRepository.queryByName(taskConfigName);
        if (taskConfigDO == null){
            throw new RuntimeException("添加任务时找不到对应的task_config");
        }
        ServiceConfigDO serviceConfigDO = serviceConfigRepository.queryByName(serviceConfigName);
        if (serviceConfigDO == null){
            throw new RuntimeException("添加任务时找不到对应的service_config");
        }

        List<String> files = FileUtil.findAllPicFiles(globalConstant.getImageDirectory() + "\\" + directory);

        if (!taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages(
                directory, files, serviceConfigName, taskConfigName)){
            throw new RuntimeException("双写:任务实例和原子任务失败, 可能是参数问题, 请检查是否未传入目录/服务配置/任务配置，以及是否这个目录没有任何图片文件" +
                    ",directory:[" + directory + "], files size:[" + files.size() + "], serviceConfigName:[" + serviceConfigName +
                    "], taskConfigName:[" + taskConfigName + "]");
        }
    }

    /**
     * 执行任务，供定时器调用
     *
     * @author blacksea3(jxt)
     * @date 2020/8/3
     */
    @Override
    public void executeTask() {
        //TODO:因为设计问题:以下两个按理在一个事务，可是却拆开了。将导致任务实例可能被重复读取.
        TaskInstanceDO taskInstanceDO = taskInstanceRepository.queryHighestPriority();
        if (taskInstanceDO == null){
            return;
        }

        List<Integer> taskDetails = taskInstanceRepository.updateStatusByTotalNumAndHandleNum(taskInstanceDO.getId());
        if (taskDetails.isEmpty()){
            LoggerUtil.error(LOGGER, "executeTask:原子任务ID列表长度为空");
            return;
        }

        if (taskDetails.size() > 1){
            LoggerUtil.error(LOGGER, "executeTask:原子任务ID列表长度>1");
            return;
        }

        Integer taskDetailID = taskDetails.get(0);
        LoggerUtil.info(LOGGER, "获得原子任务, ID为", String.valueOf(taskDetailID));

        //获取taskDetail
        TaskDetailDO taskDetailDO = taskDetailRepository.queryById(taskDetailID);
        if (taskDetailDO == null){
            LoggerUtil.error(LOGGER, "executeTask:原子任务查找为空, 内部逻辑出错");
            return;
        }

        //获取文件名, 获取服务
        String fileName = taskDetailDO.getContent();
        ServiceConfigDO serviceConfigDO = serviceConfigRepository.queryByName(taskDetailDO.getServiceName());
        if (serviceConfigDO == null){
            String temp = "服务配置查找为空, 内部逻辑出错";
            LoggerUtil.error(LOGGER, temp);
            if (!taskDetailRepository.setFailStatusAndUpdateContent(taskDetailID, temp)){
                LoggerUtil.error(LOGGER, "executeTask:更新原子任务状态和内容出错, taskDetailID:[", String.valueOf(taskDetailID), "]");
            }
            return;
        }

        //不检查bean类型了, 不检查其他字段了
        String beanType = serviceConfigDO.getBeanType();
        String beanName = serviceConfigDO.getBeanName();

        //通过Bean名获取服务
        ImageStyle imageStyle = null;
        try {
            imageStyle = (ImageStyle) SpringUtil.getBean(beanName);
        }catch (Exception e){
            String temp = "找不到Bean, beanName:[" + beanName + "]";
            LoggerUtil.error(LOGGER, e, temp);
            if (!taskDetailRepository.setFailStatusAndUpdateContent(taskDetailID, temp)){
                LoggerUtil.error(LOGGER, "executeTask:更新原子任务状态和内容出错, taskDetailID:[", String.valueOf(taskDetailID), "]");
            }
            return;
        }

        String directory = fileName.substring(0, fileName.lastIndexOf("\\"));
        String refName = directory + "\\ref.png";

        try {
            String retUrl = imageStyle.changeImageStyle(refName, fileName);
            if (retUrl == null){
                String temp = "topAlg返回结果为null, 请查看日志";
                LoggerUtil.error(LOGGER, temp);
                if (!taskDetailRepository.setFailStatusAndUpdateContent(taskDetailID, temp)){
                    LoggerUtil.error(LOGGER, "executeTask:更新原子任务状态和内容出错, taskDetailID:[", String.valueOf(taskDetailID), "]");
                }
                return;
            }
            String retFileName = FileUtil.downloadFromRemoteUrl(retUrl, directory, "res.jpg");
            if (!taskDetailRepository.setSuccessStatusAndUpdateContent(taskDetailID, retFileName)){
                LoggerUtil.error(LOGGER, "executeTask:更新原子任务状态和内容出错, taskDetailID:[", String.valueOf(taskDetailID), "]");
            }
            //return;
        }catch (ClientException | IOException e) {
            String temp = "处理图片出错, refName:[" + refName + "], fileName:[" + fileName + "]";
            LoggerUtil.error(LOGGER, e, temp);
            if (!taskDetailRepository.setFailStatusAndUpdateContent(taskDetailID, temp)){
                LoggerUtil.error(LOGGER, "executeTask:更新原子任务状态和内容出错, taskDetailID:[", String.valueOf(taskDetailID), "]");
            }
            //return;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of ImageTaskImpl has been initialized!");
    }
}
