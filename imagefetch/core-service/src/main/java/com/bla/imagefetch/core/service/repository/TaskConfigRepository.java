package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;

/**
 * InterfaceName TaskConfigRepository
 * 任务配置仓库接口
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 23:06
 */
public interface TaskConfigRepository {
    Integer insert(TaskConfigDO taskConfigDO);
    Integer update(TaskConfigDO taskConfigDO);
    Integer deleteById(Integer id);
    TaskConfigDO queryById(Integer id);
    TaskConfigDO queryByName(String name);
}
