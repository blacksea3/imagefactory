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
    Long insert(ServiceConfigDO serviceConfigDO);
    Long update(ServiceConfigDO serviceConfigDO);
    Long deleteById(Integer id);
    ServiceConfigDO queryById(Integer id);
    ServiceConfigDO queryByName(String name);
}
