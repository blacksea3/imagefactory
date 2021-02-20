package com.bla.imagefetch.common.dal.imagefactory.auto.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import java.util.List;
import com.bla.imagefetch.common.dal.imagefactory.auto.mapper.ServiceConfigDOMapper;

/**
* The Table service_config.
* 服务配置表
*/
@Repository
public class ServiceConfigDAO{

    @Autowired
    private ServiceConfigDOMapper serviceConfigDOMapper;

    /**
     * desc:不带Id插入表:service_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer insertWithoutID(ServiceConfigDO entity){
        return serviceConfigDOMapper.insertWithoutID(entity);
    }
    /**
     * desc:带Id插入表:service_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer insertWithID(ServiceConfigDO entity){
        return serviceConfigDOMapper.insertWithID(entity);
    }
    /**
     * desc:根据Id全量更新表:service_config.<br/>
     * @param entity entity
     * @return Integer
     */
    public Integer updateAll(ServiceConfigDO entity){
        return serviceConfigDOMapper.updateAll(entity);
    }
    /**
     * desc:根据Id删除数据:service_config.<br/>
     * @param id id
     * @return Integer
     */
    public Integer deleteById(Integer id){
        return serviceConfigDOMapper.deleteById(id);
    }
    /**
     * desc:根据Id获取数据:service_config.<br/>
     * @param id id
     * @return ServiceConfigDO
     */
    public ServiceConfigDO queryById(Integer id){
        return serviceConfigDOMapper.queryById(id);
    }
    /**
     * desc:根据普通索引Name获取数据:service_config.<br/>
     * @param name name
     * @return ServiceConfigDO
     */
    public ServiceConfigDO queryByName(String name){
        return serviceConfigDOMapper.queryByName(name);
    }
    /**
     * desc:查询所有服务配置.<br/>
     * @return List<ServiceConfigDO>
     */
    public List<ServiceConfigDO> queryAll(){
        return serviceConfigDOMapper.queryAll();
    }
}
