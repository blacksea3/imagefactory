package com.bla.imagefetch.upper.service.task;

import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;

/**
 * 图片任务接口
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 20:51
 */
public interface ImageTask {
    Integer addServiceConfig(ServiceConfigVO serviceConfigVO) throws RuntimeException;
    Integer addTaskConfig(TaskConfigVO taskConfigVO) throws RuntimeException;
    void addTasks(TaskInstanceAndDetailVO taskInstanceAndDetailVO) throws RuntimeException;

    void executeTask();
}
