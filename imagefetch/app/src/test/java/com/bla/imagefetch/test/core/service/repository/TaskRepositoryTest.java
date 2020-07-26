package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName TaskRepositoryTest
 * 原子任务表仓库 测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:14
 */
@SpringBootTest
@MapperScan("com.bla.imagefetch.common.dal.imagefactory.auto.mapper")
public class TaskRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRepositoryTest.class);

    @Autowired
    TaskDetailRepository taskDetailRepository;

    /**
     * Description: 简单测试:删除和单项带Id插入
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskDetailDeleteAndInsert() {
        LoggerUtil.info(LOGGER, "test started...");

        taskDetailRepository.deleteById(-2);
        Assertions.assertEquals(0, taskDetailRepository.deleteById(-2));
        Assertions.assertNull(taskDetailRepository.deleteById(null));

        Assertions.assertNull(taskDetailRepository.queryById(-2));
        Assertions.assertNull(taskDetailRepository.queryById(null));

        taskDetailRepository.deleteById(-1);
        TaskDetailDO taskDetailDO = new TaskDetailDO();
        taskDetailDO.setContent("con");
        taskDetailDO.setExtInfo("ext");
        taskDetailDO.setId(-1);
        taskDetailDO.setInstanceName("example_instant_name");
        taskDetailDO.setScript("scr");
        taskDetailDO.setServiceName("service_name");

        Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));

        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));
        Assertions.assertEquals(1, taskDetailRepository.deleteById(-1));
    }

    private boolean compareTaskDetailDO(TaskDetailDO expected, TaskDetailDO actual){
        if (expected == null){
            return actual == null;
        }else{
            return (expected.getId().equals(actual.getId())) &&
                    (expected.getContent().equals(actual.getContent())) &&
                    (expected.getExtInfo().equals(actual.getExtInfo())) &&
                    (expected.getInstanceName().equals(actual.getInstanceName())) &&
                    (expected.getScript().equals(actual.getScript())) &&
                    (expected.getServiceName().equals(actual.getServiceName()));
        }
    }

}
