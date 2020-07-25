package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.TaskDetailDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName TaskDetailRepositoryImpl
 * 原子任务表仓库 实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:13
 */
public class TaskDetailRepositoryImpl implements TaskDetailRepository, InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskDetailRepositoryImpl.class);

    @Autowired
    TaskDetailDOMapper taskDetailDOMapper;

    @Override
    public Long insert(TaskDetailDO entity) {
        return taskDetailDOMapper.insert(entity);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of TaskDetailRepositoryImpl has been initialized!");
    }
}
