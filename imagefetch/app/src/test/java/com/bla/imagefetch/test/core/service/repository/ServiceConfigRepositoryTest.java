package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName ServiceConfigRepositoryTest <br/>
 * 服务配置仓库 测试类
 *
 * @author blacksea3(jxt) <br/>
 * @date 2020/7/26 22:52<br/>
 */
@SpringBootTest
public class ServiceConfigRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceConfigRepositoryTest.class);

    @Autowired
    ServiceConfigRepository serviceConfigRepository;

    /**
     * Description: 简单测试:删除和单项带Id插入
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testServiceConfigDeleteAndInsert() {
        LoggerUtil.info(LOGGER, "testServiceConfigDeleteAndInsert() started...");

        serviceConfigRepository.deleteById(-2);
        Assertions.assertEquals(0, serviceConfigRepository.deleteById(-2));
        Assertions.assertNull(serviceConfigRepository.deleteById(null));

        Assertions.assertNull(serviceConfigRepository.queryById(-2));
        Assertions.assertNull(serviceConfigRepository.queryById(null));

        serviceConfigRepository.deleteById(-1);
        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setBeanName("bn");
        serviceConfigDO.setBeanType("bt");
        serviceConfigDO.setExtInfo("ext");
        serviceConfigDO.setId(-1);
        serviceConfigDO.setName("na");
        serviceConfigDO.setSysName("sys_name");

        Assertions.assertEquals(1, (long) serviceConfigRepository.insert(serviceConfigDO));

        Assertions.assertTrue(compareServiceConfigDO(serviceConfigDO, serviceConfigRepository.queryById(-1)));
        Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));
    }

    /**
     * Description: 测试:全量更新
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testServiceConfigUpdate(){
        LoggerUtil.info(LOGGER, "testServiceConfigUpdate() started...");

        serviceConfigRepository.deleteById(-1);
        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setBeanName("bn");
        serviceConfigDO.setBeanType("bt");
        serviceConfigDO.setExtInfo("ext");
        serviceConfigDO.setId(-1);
        serviceConfigDO.setName("na");
        serviceConfigDO.setSysName("sys_name");

        Assertions.assertEquals(1, (long) serviceConfigRepository.insert(serviceConfigDO));
        serviceConfigDO.setExtInfo("newext");
        Assertions.assertEquals(1, serviceConfigRepository.update(serviceConfigDO));
        Assertions.assertTrue(compareServiceConfigDO(serviceConfigDO, serviceConfigRepository.queryById(-1)));

        Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));
    }

    /**
     * Description: 测试:根据name返回任务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testServiceConfigQueryByName(){
        LoggerUtil.info(LOGGER, "testServiceConfigQueryByName() started...");

        serviceConfigRepository.deleteById(-1);
        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setBeanName("bn");
        serviceConfigDO.setBeanType("bt");
        serviceConfigDO.setExtInfo("ext");
        serviceConfigDO.setId(-1);
        serviceConfigDO.setName("na");
        serviceConfigDO.setSysName("sys_name");
        Assertions.assertEquals(1, (long) serviceConfigRepository.insert(serviceConfigDO));

        Assertions.assertTrue(compareServiceConfigDO(serviceConfigDO, serviceConfigRepository.queryByName("na")));

        Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));
    }

    /**
     * Description: 比较两个服务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 期待
     * @param actual: 实际
     * @return boolean 结果
     */
    private boolean compareServiceConfigDO(ServiceConfigDO expected, ServiceConfigDO actual){
        if (expected == null){
            return actual == null;
        }else{
            return (expected.getId().equals(actual.getId())) &&
                    (expected.getBeanName().equals(actual.getBeanName())) &&
                    (expected.getExtInfo().equals(actual.getExtInfo())) &&
                    (expected.getBeanType().equals(actual.getBeanType())) &&
                    (expected.getName().equals(actual.getName())) &&
                    (expected.getSysName().equals(actual.getSysName()));
        }
    }

}
