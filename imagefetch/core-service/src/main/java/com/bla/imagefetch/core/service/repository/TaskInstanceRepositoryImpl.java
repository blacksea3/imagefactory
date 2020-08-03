package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskDetailDOMapper;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskInstanceDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TaskInstanceRepositoryImpl
 * 任务实例仓库 实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 23:26
 */
@Component
public class TaskInstanceRepositoryImpl implements TaskInstanceRepository, InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskInstanceRepositoryImpl.class);

    /** 任务实例状态枚举类，初始化，运行态，结束态 */
    private enum taskInstanceStatus{
        INIT("init"),
        RUNNING("running"),
        FINISH("finish");

        private String _val;
        taskInstanceStatus(String val){
            this._val = val;
        }
    };

    @Autowired
    private TaskInstanceDOMapper taskInstanceDOMapper;

    @Autowired
    private TaskDetailDOMapper taskDetailDOMapper;

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskInstanceRepositoryImpl has been initialized!");
    }

    @Override
    public Integer insert(TaskInstanceDO taskInstanceDO) {
        if (taskInstanceDO.getId() == null){
            taskInstanceDOMapper.insertWithoutID(taskInstanceDO);
            return taskInstanceDO.getId();
        }else{
            return taskInstanceDOMapper.insertWithID(taskInstanceDO);
        }
    }

    @Override
    public Integer update(TaskInstanceDO taskInstanceDO) {
        if (taskInstanceDO.getId() == null){
            return null;
        }else{
            return taskInstanceDOMapper.updateAll(taskInstanceDO);
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskInstanceDOMapper.deleteById(id);
        }
    }

    @Override
    public TaskInstanceDO queryById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskInstanceDOMapper.queryById(id);
        }
    }

    @Override
    public TaskInstanceDO queryByName(String name) {
        if (name == null){
            return null;
        }else{
            return taskInstanceDOMapper.queryByName(name);
        }
    }

    @Override
    public TaskInstanceDO queryHighestPriority() {
        List<TaskInstanceDO> taskInstanceDOS = taskInstanceDOMapper.queryTaskInstanceNotEqualSpecificStatus(
                taskInstanceStatus.FINISH._val, 1);
        if (taskInstanceDOS.size() == 0){
            return null;
        }
        else{
            return taskInstanceDOS.get(0);
        }
    }

    /**
     * 根据总数量和已处理数量，更新已处理数量和任务实例状态
     *
     * @author blacksea3(jxt)
     * @date 2020/8/2
     * @param id:
     * @return java.util.List<java.lang.Integer> 执行结果, 空表示内部逻辑出错
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Integer> updateStatusByTotalNumAndHandleNum(Integer id) {
        if (id == null){
            return null;
        }

        TaskInstanceDO taskInstanceDO = taskInstanceDOMapper.queryByIdWithLineLock(id);
        if (taskInstanceDO == null){
            return null;
        }
        Integer handleNum = taskInstanceDO.getHandleNum();
        Integer totalNum = taskInstanceDO.getTotalNum();
        //已经全完成了，试图增加处理数量动作执行失败
        if (handleNum >= totalNum){
            return null;
        }
        //已处理数量<0
        if (handleNum < 0){
            return null;
        }

        String status = taskInstanceDO.getStatus();
        //任务实例已经结束了，为何还试图增加处理数量动作？置为失败
        if (status.equals(taskInstanceStatus.FINISH._val)){
            return null;
        }

        if (handleNum == 0){    //没有处理过任何此任务实例的任务
            if (status.equals(taskInstanceStatus.INIT._val)){   //检查任务实例状态
                if (1 != taskInstanceDOMapper.updateStatus(taskInstanceStatus.RUNNING._val, id)){
                    return null;
                }
            }else{
                return null;
            }
        }else{  //处理过此任务实例的任务
            if (status.equals(taskInstanceStatus.RUNNING._val)){
                if (handleNum == (totalNum - 1)){
                    if (1 != taskInstanceDOMapper.updateStatus(taskInstanceStatus.FINISH._val, id)){
                        return null;
                    }
                }
            }else{
                return null;
            }
        }

        List<TaskDetailDO> taskDetailDOs = taskDetailDOMapper.queryByInstanceNameAndStatus("init", taskInstanceDO.getName());
        if (taskDetailDOs.isEmpty()){
            return null;
        }else{
            TaskDetailDO taskDetailDO = taskDetailDOs.get(0);
            taskDetailRepository.setReadyStatus(taskDetailDO.getId());

            if (taskInstanceDOMapper.updateHandleNumAddOne(id) != 1){
                return null;
            }else{
                List<Integer> res = new ArrayList<>();
                res.add(taskDetailDO.getId());
                return res;
            }
        }
    }

    /**
     * 事务:RC, 双写:任务示例和原子任务，针对图片任务设置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/3
     * @param files: 文件们
     * @param directory 文件夹
     * @param serviceConfig 服务配置名
     * @param taskConfig 任务配置名
     * @return boolean
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean insertTaskInstanceAndTaskDetailForImages(String directory, List<String> files, String serviceConfig, String taskConfig) {
        if (directory == null || files == null || serviceConfig == null || taskConfig == null){
            return false;
        }

        if (files.isEmpty()){   //空的, 无需添加任务
            return false;
        }

        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();

        taskInstanceDO.setTotalNum(files.size());
        taskInstanceDO.setHandleNum(0);
        taskInstanceDO.setStatus(taskInstanceStatus.INIT._val);
        taskInstanceDO.setPriority(1);
        taskInstanceDO.setDescription("");
        taskInstanceDO.setServiceName(serviceConfig);
        taskInstanceDO.setConfigName(taskConfig);
        taskInstanceDO.setName(directory);

        taskInstanceDOMapper.insertWithoutID(taskInstanceDO);

        for (String filename:files){
            TaskDetailDO taskDetailDO = new TaskDetailDO();
            taskDetailDO.setStatus(TaskDetailRepositoryImpl.taskDetailStatus.INIT.get_val());
            taskDetailDO.setScript("");
            taskDetailDO.setExtInfo("");
            taskDetailDO.setServiceName(serviceConfig);
            taskDetailDO.setInstanceName(directory);
            taskDetailDO.setContent(filename);

            taskDetailDOMapper.insertWithoutID(taskDetailDO);
        }

        return true;
    }
}
