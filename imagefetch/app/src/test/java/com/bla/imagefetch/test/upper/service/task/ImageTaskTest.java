package com.bla.imagefetch.test.upper.service.task;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.task.ImageTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ImageTaskTest 图片任务测试
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 21:23
 */
@SpringBootTest
public class ImageTaskTest {

    @Autowired
    private ImageTask imageTask;

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @Test
    void testAddServiceConfig(){
        ServiceConfigVO serviceConfigVO = new ServiceConfigVO();
        serviceConfigVO.setBeanName("bn");
        serviceConfigVO.setBeanType(ServiceConfigVO.ENUM_SERVICE_CONFIG_VO_BEAN_TYPE.LOCAL);
        serviceConfigVO.setName("example_service_name");
        serviceConfigVO.setSysName("sa");

        Integer ret = imageTask.addServiceConfig(serviceConfigVO);

    }

    @Test
    void testAddTaskConfig(){

    }

    @Test
    void testAddTasks(){

    }

}
