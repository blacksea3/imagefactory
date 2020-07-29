package com.bla.imagefetch.common.dal.imagefactory.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import java.util.List;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskInstanceDOMapper;

/**
* The Table task_instance.
* 任务实例表
*/
@Repository
public class TaskInstanceDAO{

    @Autowired
    private TaskInstanceDOMapper taskInstanceDOMapper;

    /**
     * desc:不带Id插入表:task_instance.<br/>
     * @param entity entity
     * @return Long
     */
    public Long insertWithoutID(TaskInstanceDO entity){
        return taskInstanceDOMapper.insertWithoutID(entity);
    }
    /**
     * desc:带Id插入表:task_instance.<br/>
     * @param entity entity
     * @return Long
     */
    public Long insertWithID(TaskInstanceDO entity){
        return taskInstanceDOMapper.insertWithID(entity);
    }
    /**
     * desc:根据Id全量更新表:task_instance.<br/>
     * @param entity entity
     * @return Long
     */
    public Long updateAll(TaskInstanceDO entity){
        return taskInstanceDOMapper.updateAll(entity);
    }
    /**
     * desc:根据Id删除数据:task_instance.<br/>
     * @param id id
     * @return Long
     */
    public Long deleteById(Integer id){
        return taskInstanceDOMapper.deleteById(id);
    }
    /**
     * desc:根据Id获取数据:task_instance.<br/>
     * @param id id
     * @return TaskInstanceDO
     */
    public TaskInstanceDO queryById(Integer id){
        return taskInstanceDOMapper.queryById(id);
    }
    /**
     * desc:根据普通索引Name获取数据:task_instance.<br/>
     * @param name name
     * @return TaskInstanceDO
     */
    public TaskInstanceDO queryByName(String name){
        return taskInstanceDOMapper.queryByName(name);
    }
    /**
     * desc:查询status不为finish的任务,取最高优先级.<br/>
     * @param status status
     * @param limit limit
     * @return List<TaskInstanceDO>
     */
    public List<TaskInstanceDO> queryTaskInstanceNotEqualSpecificStatus(String status,Integer limit){
        return taskInstanceDOMapper.queryTaskInstanceNotEqualSpecificStatus(status, limit);
    }
    /**
     * desc:根据Id更新表状态.<br/>
     * @param status status
     * @param id id
     * @return Long
     */
    public Long updateStatus(String status,Integer id){
        return taskInstanceDOMapper.updateStatus(status, id);
    }
    /**
     * desc:根据Id更新表处理数量.<br/>
     * @param id id
     * @return Long
     */
    public Long updateHandleNumAddOne(Integer id){
        return taskInstanceDOMapper.updateHandleNumAddOne(id);
    }
    /**
     * desc:根据Id获取数据,带行锁:task_instance.<br/>
     * @param id id
     * @return TaskInstanceDO
     */
    public TaskInstanceDO queryByIdWithLineLock(Integer id){
        return taskInstanceDOMapper.queryByIdWithLineLock(id);
    }
}
