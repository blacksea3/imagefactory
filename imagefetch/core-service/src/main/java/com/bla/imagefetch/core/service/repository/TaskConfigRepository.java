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
    Long insert(TaskConfigDO taskConfigDO);
    Long update(TaskConfigDO taskConfigDO);
    Long deleteById(Integer id);
    TaskConfigDO queryById(Integer id);
    TaskConfigDO queryByName(String name);
}
