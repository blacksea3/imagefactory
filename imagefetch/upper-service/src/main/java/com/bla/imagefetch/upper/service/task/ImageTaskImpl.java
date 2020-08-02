package com.bla.imagefetch.upper.service.task;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;
import com.bla.imagefetch.upper.service.VO.VOConvertToDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 图片任务实现类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 20:52
 */
@Component
public class ImageTaskImpl implements ImageTask, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageTaskImpl.class);

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Override
    public void addServiceConfig(ServiceConfigVO serviceConfigVO) throws RuntimeException {
        if (serviceConfigRepository.insert(VOConvertToDO.VOServiceConfigDO(serviceConfigVO)) != 1){
            throw new RuntimeException("serviceConfigRepository.insert != 1");
        }
    }

    @Override
    public void addTaskConfig(TaskConfigVO taskConfigVO) throws RuntimeException {
        if (taskConfigRepository.insert(VOConvertToDO.VOTaskConfigDO(taskConfigVO)) != 1){
            throw new RuntimeException("taskConfigRepository.insert != 1");
        }
    }

    @Override
    public void addTasks(TaskInstanceAndDetailVO taskInstanceAndDetailVO) throws RuntimeException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of ImageTaskImpl has been initialized!");
    }
}
