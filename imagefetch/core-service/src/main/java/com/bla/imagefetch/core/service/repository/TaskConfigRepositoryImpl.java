package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskConfigDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName TaskConfigRepositoryImpl
 * 任务配置仓库 实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 23:06
 */
@Component
public class TaskConfigRepositoryImpl implements TaskConfigRepository, InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskConfigRepositoryImpl.class);

    @Autowired
    private TaskConfigDOMapper taskConfigDOMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskConfigRepositoryImpl has been initialized!");
    }

    @Override
    public Long insert(TaskConfigDO taskConfigDO) {
        if (taskConfigDO.getId() == null){
            return taskConfigDOMapper.insertWithoutID(taskConfigDO);
        }else{
            return taskConfigDOMapper.insertWithID(taskConfigDO);
        }
    }

    @Override
    public Long update(TaskConfigDO taskConfigDO) {
        if (taskConfigDO.getId() == null){
            return null;
        }else{
            return taskConfigDOMapper.updateAll(taskConfigDO);
        }
    }

    @Override
    public Long deleteById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskConfigDOMapper.deleteById(id);
        }
    }

    @Override
    public TaskConfigDO queryById(Integer id) {
        if (id == null){
            return null;
        }else{
            return taskConfigDOMapper.queryById(id);
        }
    }

    @Override
    public TaskConfigDO queryByName(String name) {
        if (name == null){
            return null;
        }else{
            return taskConfigDOMapper.queryByName(name);
        }
    }
}
