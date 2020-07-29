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
    void testTaskInstanceDeleteAndInsert() {
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
    void testTaskInstanceUpdate(){
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
    void testTaskInstanceQueryByName(){
        LoggerUtil.info(LOGGER, "testTaskConfigQueryByName() started...");

        taskInstanceRepository.deleteById(-1);
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        taskInstanceDO.setDescription("des");
        taskInstanceDO.setConfigName("cfgname");
        taskInstanceDO.setHandleNum(1);
        taskInstanceDO.setId(-1);
        taskInstanceDO.setName("na_1");
        taskInstanceDO.setStatus("sta");
        taskInstanceDO.setPriority(12);
        taskInstanceDO.setTotalNum(111);
        taskInstanceDO.setServiceName("servname");
        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO));

        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO, taskInstanceRepository.queryByName("na")));

        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
    }

    @Test
    void taskTaskInstanceQueryHighestInstance(){
        LoggerUtil.info(LOGGER, "taskTaskInstanceQueryHighestInstance() started...");

        taskInstanceRepository.deleteById(-1);
        TaskInstanceDO taskInstanceDO_1 = new TaskInstanceDO();
        taskInstanceDO_1.setDescription("des");
        taskInstanceDO_1.setConfigName("cfgname");
        taskInstanceDO_1.setHandleNum(1);
        taskInstanceDO_1.setId(-1);
        taskInstanceDO_1.setName("na_2");
        taskInstanceDO_1.setStatus("running");
        taskInstanceDO_1.setPriority(2);
        taskInstanceDO_1.setTotalNum(111);
        taskInstanceDO_1.setServiceName("servname");
        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_1));

        taskInstanceRepository.deleteById(-2);
        TaskInstanceDO taskInstanceDO_2 = new TaskInstanceDO();
        taskInstanceDO_2.setDescription("des");
        taskInstanceDO_2.setConfigName("cfgname");
        taskInstanceDO_2.setHandleNum(1);
        taskInstanceDO_2.setId(-2);
        taskInstanceDO_2.setName("na");
        taskInstanceDO_2.setStatus("init");
        taskInstanceDO_2.setPriority(1);
        taskInstanceDO_2.setTotalNum(111);
        taskInstanceDO_2.setServiceName("servname");
        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_2));

        taskInstanceRepository.deleteById(-3);
        TaskInstanceDO taskInstanceDO_3 = new TaskInstanceDO();
        taskInstanceDO_3.setDescription("des");
        taskInstanceDO_3.setConfigName("cfgname");
        taskInstanceDO_3.setHandleNum(1);
        taskInstanceDO_3.setId(-3);
        taskInstanceDO_3.setName("na_3");
        taskInstanceDO_3.setStatus("finish");
        taskInstanceDO_3.setPriority(0);
        taskInstanceDO_3.setTotalNum(111);
        taskInstanceDO_3.setServiceName("servname");
        Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_3));

        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_2, taskInstanceRepository.queryHighestPriority()));

        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-2));
        Assertions.assertEquals(1, taskInstanceRepository.deleteById(-3));
    }

    @Test
    void testTaskInstanceUpdateStatusByTotalNumAndHandleNum(){
        LoggerUtil.info(LOGGER, "testTaskInstanceUpdateStatusByTotalNumAndHandleNum() started...");

        {
            //正常: 未处理->正在运行
            taskInstanceRepository.deleteById(-1);
            TaskInstanceDO taskInstanceDO_1 = new TaskInstanceDO();
            taskInstanceDO_1.setDescription("des");
            taskInstanceDO_1.setConfigName("cfgname");
            taskInstanceDO_1.setHandleNum(0);
            taskInstanceDO_1.setId(-1);
            taskInstanceDO_1.setName("na_2");
            taskInstanceDO_1.setStatus("init");
            taskInstanceDO_1.setPriority(2);
            taskInstanceDO_1.setTotalNum(3);
            taskInstanceDO_1.setServiceName("servname");
            Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_1));

            Assertions.assertTrue(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            taskInstanceDO_1.setHandleNum(1);
            taskInstanceDO_1.setStatus("running");
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //正常: 正在运行->正在运行
            Assertions.assertTrue(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            taskInstanceDO_1.setHandleNum(2);
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //正常: 正在运行->结束
            Assertions.assertTrue(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            taskInstanceDO_1.setHandleNum(3);
            taskInstanceDO_1.setStatus("finish");
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //删除数据
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        }

        {
            //异常1: id不存在或id=null
            taskInstanceRepository.deleteById(-1);
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(null));

            //异常2: 状态已经是finish
            TaskInstanceDO taskInstanceDO_1 = new TaskInstanceDO();
            taskInstanceDO_1.setDescription("des");
            taskInstanceDO_1.setConfigName("cfgname");
            taskInstanceDO_1.setHandleNum(0);
            taskInstanceDO_1.setId(-1);
            taskInstanceDO_1.setName("na_2");
            taskInstanceDO_1.setStatus("finish");
            taskInstanceDO_1.setPriority(2);
            taskInstanceDO_1.setTotalNum(3);
            taskInstanceDO_1.setServiceName("servname");
            Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常3: 在handleNum=0时状态为running
            taskInstanceDO_1.setStatus("running");
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常4: 在handleNum!=0时状态为init
            taskInstanceDO_1.setStatus("init");
            taskInstanceDO_1.setHandleNum(1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常5: handleNum >= totalNum
            taskInstanceDO_1.setHandleNum(taskInstanceDO_1.getTotalNum());
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            taskInstanceDO_1.setHandleNum(taskInstanceDO_1.getTotalNum() + 1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常6: totalNum < 0
            taskInstanceDO_1.setHandleNum(-1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertFalse(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //删除数据
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        }

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
