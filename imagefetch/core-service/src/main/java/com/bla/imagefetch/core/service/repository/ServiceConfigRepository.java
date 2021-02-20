package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;

import java.util.List;

/**
 * InterfaceName ServiceConfigRepository
 * 服务配置仓库接口
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 22:46
 */
public interface ServiceConfigRepository {
    Integer insert(ServiceConfigDO serviceConfigDO);
    Integer update(ServiceConfigDO serviceConfigDO);
    Integer deleteById(Integer id);
    ServiceConfigDO queryById(Integer id);
    ServiceConfigDO queryByName(String name);
    List<ServiceConfigDO> queryAll();
}
