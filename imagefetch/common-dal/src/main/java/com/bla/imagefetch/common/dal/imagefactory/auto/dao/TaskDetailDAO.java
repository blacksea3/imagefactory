package com.bla.imagefetch.common.dal.imagefactory.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import java.util.List;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskDetailDOMapper;

/**
* The Table task_detail.
* 原子任务表
*/
@Repository
public class TaskDetailDAO{

    @Autowired
    private TaskDetailDOMapper taskDetailDOMapper;

    /**
     * desc:不带Id插入表:task_detail.<br/>
     * @param entity entity
     * @return Long
     */
    public Long insertWithoutID(TaskDetailDO entity){
        return taskDetailDOMapper.insertWithoutID(entity);
    }
    /**
     * desc:带Id插入表:task_detail.<br/>
     * @param entity entity
     * @return Long
     */
    public Long insertWithID(TaskDetailDO entity){
        return taskDetailDOMapper.insertWithID(entity);
    }
    /**
     * desc:不带Id批量插入表:task_detail.<br/>
     * @param list list
     * @return Long
     */
    public Long insertBatchWithoutID(List<TaskDetailDO> list){
        return taskDetailDOMapper.insertBatchWithoutID(list);
    }
    /**
     * desc:根据Id全量更新表:task_detail.<br/>
     * @param entity entity
     * @return Long
     */
    public Long updateAll(TaskDetailDO entity){
        return taskDetailDOMapper.updateAll(entity);
    }
    /**
     * desc:根据Id删除数据:task_detail.<br/>
     * @param id id
     * @return Long
     */
    public Long deleteById(Integer id){
        return taskDetailDOMapper.deleteById(id);
    }
    /**
     * desc:根据Id获取数据:task_detail.<br/>
     * @param id id
     * @return TaskDetailDO
     */
    public TaskDetailDO queryById(Integer id){
        return taskDetailDOMapper.queryById(id);
    }
    /**
     * desc:根据普通索引InstanceName获取数据:task_detail.<br/>
     * @param instanceName instanceName
     * @return List<TaskDetailDO>
     */
    public List<TaskDetailDO> queryByInstanceName(String instanceName){
        return taskDetailDOMapper.queryByInstanceName(instanceName);
    }
}
