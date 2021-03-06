package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;

import java.util.List;

/**
 * InterfaceName TaskInstanceRepository
 * 任务实例仓库接口
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 23:25
 */
public interface TaskInstanceRepository {
    Long insert(TaskInstanceDO taskInstanceDO);
    Long update(TaskInstanceDO taskInstanceDO);
    Long deleteById(Integer id);
    TaskInstanceDO queryById(Integer id);
    TaskInstanceDO queryByName(String name);

    TaskInstanceDO queryHighestPriority();

    List<Integer> updateStatusByTotalNumAndHandleNum(Integer id);
}
