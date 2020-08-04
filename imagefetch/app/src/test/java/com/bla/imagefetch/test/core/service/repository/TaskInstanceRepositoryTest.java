package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @Autowired
    private TaskConfigRepository taskConfigRepository;

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

        Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO, taskInstanceRepository.queryByName("na_1")));

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
            List<Integer> totalRet = new ArrayList<>();
            List<Integer> tempRet;

            for (int i = -1; i > -4; --i){
                totalRet.add(i);

                taskDetailRepository.deleteById(i);
                TaskDetailDO taskDetailDO = new TaskDetailDO();
                taskDetailDO.setContent("con" + i);
                taskDetailDO.setExtInfo("ext");
                taskDetailDO.setId(i);
                taskDetailDO.setInstanceName("example_instance_name");
                taskDetailDO.setScript("scr" + i);
                taskDetailDO.setServiceName("service_name");
                taskDetailDO.setStatus("init");
                Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));
            }

            //正常: 未处理->正在运行
            taskInstanceRepository.deleteById(-1);
            TaskInstanceDO taskInstanceDO_1 = new TaskInstanceDO();
            taskInstanceDO_1.setDescription("des");
            taskInstanceDO_1.setConfigName("cfgname");
            taskInstanceDO_1.setHandleNum(0);
            taskInstanceDO_1.setId(-1);
            taskInstanceDO_1.setName("example_instance_name");
            taskInstanceDO_1.setStatus("init");
            taskInstanceDO_1.setPriority(2);
            taskInstanceDO_1.setTotalNum(3);
            taskInstanceDO_1.setServiceName("servname");
            Assertions.assertEquals(1, (long) taskInstanceRepository.insert(taskInstanceDO_1));

            tempRet = taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1);
            Assertions.assertNotNull(tempRet);
            Assertions.assertTrue(LeftListContainRight(totalRet, tempRet));
            taskInstanceDO_1.setHandleNum(1);
            taskInstanceDO_1.setStatus("running");
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //正常: 正在运行->正在运行

            tempRet = taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1);
            Assertions.assertNotNull(tempRet);
            Assertions.assertTrue(LeftListContainRight(totalRet, tempRet));
            taskInstanceDO_1.setHandleNum(2);
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //正常: 正在运行->结束
            tempRet = taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1);
            Assertions.assertNotNull(tempRet);
            Assertions.assertTrue(LeftListContainRight(totalRet, tempRet));
            taskInstanceDO_1.setHandleNum(3);
            taskInstanceDO_1.setStatus("finish");
            Assertions.assertTrue(compareTaskInstanceDO(taskInstanceDO_1, taskInstanceRepository.queryById(-1)));

            //删除数据
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        }

        {
            //异常1: id不存在或id=null
            taskInstanceRepository.deleteById(-1);
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(null));

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
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常3: 在handleNum=0时状态为running
            taskInstanceDO_1.setStatus("running");
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常4: 在handleNum!=0时状态为init
            taskInstanceDO_1.setStatus("init");
            taskInstanceDO_1.setHandleNum(1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常5: handleNum >= totalNum
            taskInstanceDO_1.setHandleNum(taskInstanceDO_1.getTotalNum());
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            taskInstanceDO_1.setHandleNum(taskInstanceDO_1.getTotalNum() + 1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //异常6: totalNum < 0
            taskInstanceDO_1.setHandleNum(-1);
            Assertions.assertEquals(1, (long) taskInstanceRepository.update(taskInstanceDO_1));
            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //删除数据
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        }

        {
            //异常:找不到init的原子任务
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

            Assertions.assertNull(taskInstanceRepository.updateStatusByTotalNumAndHandleNum(-1));

            //删除数据
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(-1));
        }
    }

    @Test
    void testInsertTaskInstanceAndTaskDetailForImages(){
        {
            List<String> files = new ArrayList<>();
            files.add("ddd");
            //参数存在null
            Assertions.assertFalse(taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages(null, files, "", ""));
            Assertions.assertFalse(taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages("", null, "", ""));
            Assertions.assertFalse(taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages("", files, null, ""));
            Assertions.assertFalse(taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages("", files, "", null));

            //files为空列表
            files.clear();
            Assertions.assertFalse(taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages("", files, "", ""));
        }

        {
            //正常
            List<String> files = new ArrayList<>();
            files.add("test_filename_1");
            files.add("test_filename_2");

            Assertions.assertTrue(
                    taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages(
                            "example_dir", files, "example_service_config_name", "example_task_config_name")
            );

            TaskInstanceDO expected = new TaskInstanceDO();
            expected.setTotalNum(files.size());
            expected.setHandleNum(0);
            expected.setStatus("init");
            expected.setPriority(1);
            expected.setDescription("");
            expected.setServiceName("example_service_config_name");
            expected.setConfigName("example_task_config_name");
            expected.setName("example_dir");

            TaskInstanceDO actual = taskInstanceRepository.queryByName("example_dir");
            Assertions.assertTrue(compareTaskInstanceDOWithoutID(expected, actual));

            TaskDetailRepositoryTest taskDetailRepositoryTest = new TaskDetailRepositoryTest();

            List<TaskDetailDO> actuals = taskDetailRepository.queryByInstanceNameAndStatus("example_dir", null);

            Assertions.assertEquals(2, actuals.size());

            for (String filename:files){
                TaskDetailDO taskDetailDO = new TaskDetailDO();
                taskDetailDO.setStatus(TaskDetailRepositoryImpl.taskDetailStatus.INIT.get_val());
                taskDetailDO.setScript("");
                taskDetailDO.setExtInfo("");
                taskDetailDO.setServiceName("example_service_config_name");
                taskDetailDO.setInstanceName("example_dir");
                taskDetailDO.setContent(filename);

                Assertions.assertTrue(taskDetailRepositoryTest.compareTaskDetailDOWithoutID(taskDetailDO, actuals.get(0)) ||
                        taskDetailRepositoryTest.compareTaskDetailDOWithoutID(taskDetailDO, actuals.get(1)));
            }

            Assertions.assertEquals(1, taskInstanceRepository.deleteById(actual.getId()));
            Assertions.assertEquals(1, taskDetailRepository.deleteById(actuals.get(0).getId()));
            Assertions.assertEquals(1, taskDetailRepository.deleteById(actuals.get(1).getId()));
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
            if (actual == null){
                return false;
            }
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

    /**
     * Description: 比较两个任务实例, 不带ID
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 期待
     * @param actual: 实际
     * @return boolean 结果
     */
    public boolean compareTaskInstanceDOWithoutID(TaskInstanceDO expected, TaskInstanceDO actual){
        if (expected == null){
            return actual == null;
        }else{
            if (actual == null){
                return false;
            }
            return ((expected.getDescription().equals(actual.getDescription())) &&
                    (expected.getConfigName().equals(actual.getConfigName())) &&
                    (expected.getServiceName().equals(actual.getServiceName())) &&
                    (expected.getName().equals(actual.getName())) &&
                    (expected.getHandleNum().equals(actual.getHandleNum())) &&
                    (expected.getTotalNum().equals(actual.getTotalNum())) &&
                    (expected.getPriority().equals(actual.getPriority())) &&
                    (expected.getStatus().equals(actual.getStatus())));
        }
    }

    private boolean LeftListContainRight(List<Integer> left, List<Integer> right){
        for (Integer val:right){
            if (!left.contains(val)){
                return false;
            }
        }
        return true;
    }

}
