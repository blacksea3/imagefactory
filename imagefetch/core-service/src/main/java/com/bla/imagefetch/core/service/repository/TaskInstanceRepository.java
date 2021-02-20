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
    Integer insert(TaskInstanceDO taskInstanceDO);
    Integer update(TaskInstanceDO taskInstanceDO);
    Integer deleteById(Integer id);
    TaskInstanceDO queryById(Integer id);
    TaskInstanceDO queryByName(String name);

    TaskInstanceDO queryHighestPriority();
    List<TaskInstanceDO> queryAllTaskInstances();
    
    List<Integer> updateStatusByTotalNumAndHandleNum(Integer id);
    boolean insertTaskInstanceAndTaskDetailForImages(String directory, List<String> files, String serviceConfig, String taskConfig);

    Integer enableTaskInstance(Integer id);
}
