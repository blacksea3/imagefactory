package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName taskInstanceRepositoryTest
 * 任务配置仓库 测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 22:52
 */
@SpringBootTest
public class TaskInstanceRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskInstanceRepositoryTest.class);

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    /**
     * Description: 简单测试:删除和单项带Id插入
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskConfigDeleteAndInsert() {
        LoggerUtil.info(LOGGER, "testTaskConfigDeleteAndInsert() started...");

        taskInstanceRepository.deleteById(-2);
        Assertions.assertEquals(0, taskInstanceRepository.deleteById(-2));
        Assertions.assertNull(taskInstanceRepository.deleteById(null));

        Assertions.assertNull(taskInstanceRepository.queryById(-2));
        Assertions.assertNull(taskInstanceRepository.queryById(null));

        taskInstanceRepository.deleteById(-1);
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        taskInstanceDO.setDescription("des");
        taskInstanceDO.setConfigName("cfgname");
        taskInstanceDO.setHandleNum(1);
        taskInstanceDO.setId(-1);
        taskInstanceDO.setName("na");
        taskInstanceDO.setStatus("sta");
        taskInstanceDO.setPriority(12);
        taskInstanceDO.setTotalNum(111);
        taskInstanceDO.setServiceName("servname");

        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO));

        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO, taskInstanceRepository.queryById(-1)));
        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
    }

    /**
     * Description: 测试:全量更新
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskConfigUpdate(){
        LoggerUtil.info(LOGGER, "testTaskConfigUpdate() started...");

        taskInstanceRepository.deleteById(-1);
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        taskInstanceDO.setDescription("des");
        taskInstanceDO.setConfigName("cfgname");
        taskInstanceDO.setHandleNum(1);
        taskInstanceDO.setId(-1);
        taskInstanceDO.setName("na");
        taskInstanceDO.setStatus("sta");
        taskInstanceDO.setPriority(12);
        taskInstanceDO.setTotalNum(111);
        taskInstanceDO.setServiceName("servname");

        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO));
        taskInstanceDO.setServiceName("new_servname");
        Assertions.assertEquals(1, taskInstanceRepository.update(taskInstanceDO));
        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO, taskInstanceRepository.queryById(-1)));

        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
    }

    /**
     * Description: 测试:根据name返回任务实例
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskConfigQueryByName(){
        LoggerUtil.info(LOGGER, "testTaskConfigQueryByName() started...");

        taskInstanceRepository.deleteById(-1);
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        taskInstanceDO.setDescription("des");
        taskInstanceDO.setConfigName("cfgname");
        taskInstanceDO.setHandleNum(1);
        taskInstanceDO.setId(-1);
        taskInstanceDO.setName("na");
        taskInstanceDO.setStatus("sta");
        taskInstanceDO.setPriority(12);
        taskInstanceDO.setTotalNum(111);
        taskInstanceDO.setServiceName("servname");
        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO));

        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO, taskInstanceRepository.queryByName("na")));

        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
    }

    /**
     * Description: 比较两个任务实例
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 期待
     * @param actual: 实际
     * @return boolean 结果
     */
    private boolean compareTaskInstanceDO(TaskInstanceDO expected, TaskInstanceDO actual){
        if (expected == null){
            return actual == null;
        }else{
            return (expected.getId().equals(actual.getId())) &&
                    (expected.getDescription().equals(actual.getDescription())) &&
                    (expected.getConfigName().equals(actual.getConfigName())) &&
                    (expected.getServiceName().equals(actual.getServiceName())) &&
                    (expected.getName().equals(actual.getName())) &&
                    (expected.getHandleNum().equals(actual.getHandleNum())) &&
                    (expected.getTotalNum().equals(actual.getTotalNum())) &&
                    (expected.getPriority().equals(actual.getPriority())) &&
                    (expected.getStatus().equals(actual.getStatus()));
        }
    }

}
