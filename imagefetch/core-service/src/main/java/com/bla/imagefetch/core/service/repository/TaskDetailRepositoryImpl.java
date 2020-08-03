package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskDetailDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName TaskDetailRepositoryImpl
 * 原子任务表仓库 实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:13
 */
@Component
public class TaskDetailRepositoryImpl implements TaskDetailRepository, InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskDetailRepositoryImpl.class);

    @Autowired
    TaskDetailDOMapper taskDetailDOMapper;

    /** 原子任务状态枚举类，初始化，准备态，成功态，失败态 */
    public enum taskDetailStatus{
        INIT("init"),
        READY("ready"),
        SUCCESS("success"),
        FAIL("fail");

        private String _val;
        taskDetailStatus(String val){
            this._val = val;
        }

        public String get_val() {
            return _val;
        }
    };

    @Override
    public Integer insert(TaskDetailDO entity) {
        if (entity.getId() == null){
            taskDetailDOMapper.insertWithoutID(entity);
            return entity.getId();
        }else{
            return taskDetailDOMapper.insertWithID(entity);
        }
    }

    @Override
    public Integer updateFields(TaskDetailDO entity) {
        if (entity.getId() == null){
            return null;
        }else{
            return taskDetailDOMapper.updateAll(entity);
        }
    }

    @Override
    public Integer setReadyStatus(Integer id) {
        if (id == null){
            return null;
        }else{
            TaskDetailDO taskDetailDO = new TaskDetailDO();
            taskDetailDO.setId(id);
            taskDetailDO.setStatus(taskDetailStatus.READY._val);
            return taskDetailDOMapper.updateAll(taskDetailDO);
        }
    }

    @Override
    public Integer setSuccessStatus(Integer id) {
        if (id == null){
            return null;
        }else{
            TaskDetailDO taskDetailDO = new TaskDetailDO();
            taskDetailDO.setId(id);
            taskDetailDO.setStatus(taskDetailStatus.SUCCESS._val);
            return taskDetailDOMapper.updateAll(taskDetailDO);
        }
    }

    @Override
    public Integer setFailStatus(Integer id) {
        if (id == null){
            return null;
        }else{
            TaskDetailDO taskDetailDO = new TaskDetailDO();
            taskDetailDO.setId(id);
            taskDetailDO.setStatus(taskDetailStatus.FAIL._val);
            return taskDetailDOMapper.updateAll(taskDetailDO);
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskDetailDOMapper.deleteById(id);
        }
    }

    @Override
    public TaskDetailDO queryById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskDetailDOMapper.queryById(id);
        }
    }

    /**
     * 根据实例名和状态查找任务们，状态可空（表示不使用此条件），实例名不可空
     *
     * @author blacksea3(jxt)
     * @date 2020/8/2
     * @param instanceName: 实例名
     * @param status: 状态
     * @return java.util.List<com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO>
     */
    @Override
    public List<TaskDetailDO> queryByInstanceNameAndStatus(String instanceName, String status) {
        if (instanceName == null){
            return null;
        }else{
            //注意参数顺序
            return taskDetailDOMapper.queryByInstanceNameAndStatus(status, instanceName);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskDetailRepositoryImpl has been initialized!");
    }
}
