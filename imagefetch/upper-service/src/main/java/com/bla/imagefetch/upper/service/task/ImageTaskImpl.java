package com.bla.imagefetch.upper.service.task;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;
import com.bla.imagefetch.upper.service.VO.VOConvertToDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片任务实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 20:52
 */
@Component
public class ImageTaskImpl implements ImageTask, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageTaskImpl.class);

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
        //TODO:根据文件夹路径寻找图片们，返回List<String>图片名
        List<String> files = new ArrayList<>();
        files.add("test_filename_1");
        files.add("test_filename_2");

        if (!taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages(
                directory, files, serviceConfigName, taskConfigName)){
            throw new RuntimeException("双写:任务实例和原子任务 失败");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of ImageTaskImpl has been initialized!");
    }
}
