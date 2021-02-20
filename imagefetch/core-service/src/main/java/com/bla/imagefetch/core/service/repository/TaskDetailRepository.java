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
    Integer insert(TaskDetailDO taskDetailDO);
    Integer updateFields(TaskDetailDO taskDetailDO);
    Integer setReadyStatus(Integer id);
    Integer setSuccessStatus(Integer id);
    Integer setFailStatus(Integer id);
    Integer deleteById(Integer id);
    TaskDetailDO queryById(Integer id);
    List<TaskDetailDO> queryByInstanceNameAndStatus(String instanceName, String status);

    boolean setSuccessStatusAndUpdateResult(Integer id, String result);
    boolean setFailStatusAndUpdateResult(Integer id, String result);
}
