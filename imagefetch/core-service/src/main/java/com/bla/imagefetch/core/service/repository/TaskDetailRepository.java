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
    Long insert(TaskDetailDO entity);
    Long update(TaskDetailDO entity);
    Long deleteById(Integer id);
    TaskDetailDO queryById(Integer id);
    List<TaskDetailDO> queryByInstanceName(String instanceName);
}
