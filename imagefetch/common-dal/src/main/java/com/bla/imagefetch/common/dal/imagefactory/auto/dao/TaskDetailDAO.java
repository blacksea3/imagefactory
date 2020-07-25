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
     * desc:插入表:task_detail.<br/>
     * @param entity entity
     * @return Long
     */
    public Long insert(TaskDetailDO entity){
        return taskDetailDOMapper.insert(entity);
    }
    /**
     * desc:批量插入表:task_detail.<br/>
     * @param list list
     * @return Long
     */
    public Long insertBatch(List<TaskDetailDO> list){
        return taskDetailDOMapper.insertBatch(list);
    }
    /**
     * desc:根据主键删除数据:task_detail.<br/>
     * @param id id
     * @return Long
     */
    public Long deleteById(Integer id){
        return taskDetailDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:task_detail.<br/>
     * @param id id
     * @return TaskDetailDO
     */
    public TaskDetailDO getById(Integer id){
        return taskDetailDOMapper.getById(id);
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
