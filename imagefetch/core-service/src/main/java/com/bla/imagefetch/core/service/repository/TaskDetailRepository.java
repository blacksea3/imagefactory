package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;

/**
 * InterfaceName TaskDetailRepository
 * 原子任务仓库接口
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:11
 */
public interface TaskDetailRepository {
    Long insert(TaskDetailDO entity);
}
