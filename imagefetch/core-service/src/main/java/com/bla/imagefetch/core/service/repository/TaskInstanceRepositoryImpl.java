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

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskInstanceRepositoryImpl has been initialized!");
    }

    @Override
    public Long insert(TaskInstanceDO taskInstanceDO) {
        if (taskInstanceDO.getId() == null){
            return taskInstanceDOMapper.insertWithoutID(taskInstanceDO);
        }else{
            return taskInstanceDOMapper.insertWithID(taskInstanceDO);
        }
    }

    @Override
    public Long update(TaskInstanceDO taskInstanceDO) {
        if (taskInstanceDO.getId() == null){
            return null;
        }else{
            return taskInstanceDOMapper.updateAll(taskInstanceDO);
        }
    }

    @Override
    public Long deleteById(Integer id) {
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
     *
     * 根据总数量和已处理数量，更新已处理数量和任务实例状态
     *
     * @author blacksea3(jxt)
     * @date 2020/7/29

     * @return boolean 执行结果
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean updateStatusByTotalNumAndHandleNum(Integer id) {
        if (id == null){
            return false;
        }

        TaskInstanceDO taskInstanceDO = taskInstanceDOMapper.queryByIdWithLineLock(id);
        if (taskInstanceDO == null){
            return false;
        }
        Integer handleNum = taskInstanceDO.getHandleNum();
        Integer totalNum = taskInstanceDO.getTotalNum();
        //已经全完成了，试图增加处理数量动作执行失败
        if (handleNum >= totalNum){
            return false;
        }
        //已处理数量<0
        if (handleNum < 0){
            return false;
        }

        String status = taskInstanceDO.getStatus();
        //任务实例已经结束了，为何还试图增加处理数量动作？置为失败
        if (status.equals(taskInstanceStatus.FINISH._val)){
            return false;
        }

        if (handleNum == 0){    //没有处理过任何此任务实例的任务
            if (status.equals(taskInstanceStatus.INIT._val)){   //检查任务实例状态
                if (1 != taskInstanceDOMapper.updateStatus(taskInstanceStatus.RUNNING._val, id)){
                    return false;
                }
            }else{
                return false;
            }
        }else{  //处理过此任务实例的任务
            if (status.equals(taskInstanceStatus.RUNNING._val)){
                if (handleNum == (totalNum - 1)){
                    if (1 != taskInstanceDOMapper.updateStatus(taskInstanceStatus.FINISH._val, id)){
                        return false;
                    }
                }
            }else{
                return false;
            }
        }

        //TODO:查找此任务实例的原子任务，如果找不到，那么返回false，否则执行taskInstanceDOMapper.updateHandleNumAddOne(id) == 1
        List<TaskDetailDO> taskDetailDOs = taskDetailDOMapper.queryByInstanceNameAndStatus("init", taskInstanceDO.getName());
        if (taskDetailDOs.isEmpty()){
            return false;
        }else{
            TaskDetailDO taskDetailDO = taskDetailDOs.get(0);
            //taskInstanceDOMapper.
        }



        return taskInstanceDOMapper.updateHandleNumAddOne(id) == 1;
    }
}
