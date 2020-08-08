package com.bla.imagefetch.common.dal.imagefactory.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import java.lang.String;
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
     * @return Integer
     */
    public Integer insertWithoutID(TaskInstanceDO entity){
        return taskInstanceDOMapper.insertWithoutID(entity);
    }
    /**
     * desc:带Id插入表:task_instance.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer insertWithID(TaskInstanceDO entity){
        return taskInstanceDOMapper.insertWithID(entity);
    }
    /**
     * desc:根据Id全量更新表:task_instance.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer updateAll(TaskInstanceDO entity){
        return taskInstanceDOMapper.updateAll(entity);
    }
    /**
     * desc:根据Id删除数据:task_instance.<br/>
     * @param id id
     * @return Integer
     */
    public Integer deleteById(Integer id){
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
     * desc:查询status在某个List中的任务,优先级排序,限定数量.<br/>
     * @param limit limit
     * @param statusList statusList
     * @return List<TaskInstanceDO>
     */
    public List<TaskInstanceDO> queryTaskInstanceEqualStatusList(Integer limit,List<String> statusList){
    if(statusList!=null && statusList.size()>201){throw new RuntimeException("statusList 超出200长度限制");}
        return taskInstanceDOMapper.queryTaskInstanceEqualStatusList(limit, statusList);
    }
    /**
     * desc:根据Id更新表状态.<br/>
     * @param status status
     * @param id id
     * @return Integer
     */
    public Integer updateStatus(String status,Integer id){
        return taskInstanceDOMapper.updateStatus(status, id);
    }
    /**
     * desc:根据Id更新表处理数量.<br/>
     * @param id id
     * @return Integer
     */
    public Integer updateHandleNumAddOne(Integer id){
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
