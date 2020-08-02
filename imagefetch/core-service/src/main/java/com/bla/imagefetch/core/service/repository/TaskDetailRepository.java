package com.bla.imagefetch.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import java.util.List;

/**
 * InterfaceName TaskDetailRepository
 * 原子任务仓库接口
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:11
 */
public interface TaskDetailRepository {
    Long insert(TaskDetailDO taskDetailDO);
    Long updateFields(TaskDetailDO taskDetailDO);
    Long setReadyStatus(Integer id);
    Long setSuccessStatus(Integer id);
    Long setFailStatus(Integer id);
    Long deleteById(Integer id);
    TaskDetailDO queryById(Integer id);
    List<TaskDetailDO> queryByInstanceNameAndStatus(String instanceName, String status);
}
