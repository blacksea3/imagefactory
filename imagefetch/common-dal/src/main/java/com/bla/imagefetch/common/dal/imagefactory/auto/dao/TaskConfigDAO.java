package com.bla.imagefetch.common.dal.imagefactory.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import java.util.List;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskConfigDOMapper;

/**
* The Table task_config.
* 任务配置表
*/
@Repository
public class TaskConfigDAO{

    @Autowired
    private TaskConfigDOMapper taskConfigDOMapper;

    /**
     * desc:不带Id插入表:task_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer insertWithoutID(TaskConfigDO entity){
        return taskConfigDOMapper.insertWithoutID(entity);
    }
    /**
     * desc:带Id插入表:task_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer insertWithID(TaskConfigDO entity){
        return taskConfigDOMapper.insertWithID(entity);
    }
    /**
     * desc:根据Id全量更新表:task_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer updateAll(TaskConfigDO entity){
        return taskConfigDOMapper.updateAll(entity);
    }
    /**
     * desc:根据Id删除数据:task_config.<br/>
     * @param id id
     * @return Integer
     */
    public Integer deleteById(Integer id){
        return taskConfigDOMapper.deleteById(id);
    }
    /**
     * desc:根据Id获取数据:task_config.<br/>
     * @param id id
     * @return TaskConfigDO
     */
    public TaskConfigDO queryById(Integer id){
        return taskConfigDOMapper.queryById(id);
    }
    /**
     * desc:根据普通索引Name获取数据:task_config.<br/>
     * @param name name
     * @return TaskConfigDO
     */
    public TaskConfigDO queryByName(String name){
        return taskConfigDOMapper.queryByName(name);
    }
    /**
     * desc:查询所有任务配置.<br/>
     * @return List<TaskConfigDO>
     */
    public List<TaskConfigDO> queryAll(){
        return taskConfigDOMapper.queryAll();
    }
}
