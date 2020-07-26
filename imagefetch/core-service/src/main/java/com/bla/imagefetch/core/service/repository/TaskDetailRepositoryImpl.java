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

    @Override
    public Long insert(TaskDetailDO entity) {
        if (entity.getId() == null){
            return taskDetailDOMapper.insertWithoutID(entity);
        }else{
            return taskDetailDOMapper.insertWithID(entity);
        }
    }

    @Override
    public Long update(TaskDetailDO entity) {
        if (entity.getId() == null){
            return null;
        }else{
            return taskDetailDOMapper.updateAll(entity);
        }
    }

    @Override
    public Long deleteById(Integer id) {
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

    @Override
    public List<TaskDetailDO> queryByInstanceName(String instanceName) {
        if (instanceName == null){
            return null;
        }else{
            return taskDetailDOMapper.queryByInstanceName(instanceName);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskDetailRepositoryImpl has been initialized!");
    }
}
