package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.ServiceConfigDOMapper;
import com.bla.imagefetch.common.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName ServiceConfigRepositoryImpl
 * 服务配置仓库 实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 22:46
 */
@Component
public class ServiceConfigRepositoryImpl implements ServiceConfigRepository, InitializingBean {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceConfigRepositoryImpl.class);

    @Autowired
    private ServiceConfigDOMapper serviceConfigDOMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of ServiceConfigRepositoryImpl has been initialized!");
    }

    @Override
    public Integer insert(ServiceConfigDO serviceConfigDO) {
        if (serviceConfigDO.getId() == null){
            serviceConfigDOMapper.insertWithoutID(serviceConfigDO);
            return serviceConfigDO.getId();
        }else{
            return serviceConfigDOMapper.insertWithID(serviceConfigDO);
        }
    }

    @Override
    public Integer update(ServiceConfigDO serviceConfigDO) {
        if (serviceConfigDO.getId() == null){
            return null;
        }else{
            return serviceConfigDOMapper.updateAll(serviceConfigDO);
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        if (id == null){
            return null;
        }else{
            return serviceConfigDOMapper.deleteById(id);
        }
    }

    @Override
    public ServiceConfigDO queryById(Integer id) {
        if (id == null){
            return null;
        }else{
            return serviceConfigDOMapper.queryById(id);
        }
    }

    @Override
    public ServiceConfigDO queryByName(String name) {
        if (name == null){
            return null;
        }else{
            return serviceConfigDOMapper.queryByName(name);
        }
    }

    @Override
    public List<ServiceConfigDO> queryAll() {
        return serviceConfigDOMapper.queryAll();
    }
}
