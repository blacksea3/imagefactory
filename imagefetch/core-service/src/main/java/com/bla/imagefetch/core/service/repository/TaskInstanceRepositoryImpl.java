package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskInstanceDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private TaskInstanceDOMapper taskInstanceDOMapper;

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
}
