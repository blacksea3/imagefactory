package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import com.bla.imagefetch.test.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TaskDetailRepositoryTest
 * 原子任务仓库 测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/25 23:14
 */
@SpringBootTest
public class TaskDetailRepositoryTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskDetailRepositoryTest.class);

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
        LoggerUtil.info(LOGGER, "testTaskDetailDeleteAndInsert() started...");

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
        taskDetailDO.setStatus("sta");
        taskDetailDO.setResult("");

        Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));

        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));
        Assertions.assertEquals(1, taskDetailRepository.deleteById(-1));
    }

    /**
     * Description: 测试:全量更新
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskDetailUpdate(){
        LoggerUtil.info(LOGGER, "testTaskDetailUpdate() started...");

        taskDetailRepository.deleteById(-1);
        TaskDetailDO taskDetailDO = new TaskDetailDO();
        taskDetailDO.setContent("con");
        taskDetailDO.setExtInfo("ext");
        taskDetailDO.setId(-1);
        taskDetailDO.setInstanceName("example_instant_name");
        taskDetailDO.setScript("scr");
        taskDetailDO.setServiceName("service_name");
        taskDetailDO.setStatus("sta");
        taskDetailDO.setResult("");

        Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));
        taskDetailDO.setExtInfo("newext");
        Assertions.assertEquals(1, taskDetailRepository.updateFields(taskDetailDO));
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertEquals(1, taskDetailRepository.setReadyStatus(-1));
        taskDetailDO.setStatus("ready");
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertEquals(1, taskDetailRepository.setFailStatus(-1));
        taskDetailDO.setStatus("fail");
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertEquals(1, taskDetailRepository.setSuccessStatus(-1));
        taskDetailDO.setStatus("success");
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertTrue(taskDetailRepository.setFailStatusAndUpdateResult(-1, "re1"));
        taskDetailDO.setStatus("fail");
        taskDetailDO.setResult("re1");
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertTrue(taskDetailRepository.setSuccessStatusAndUpdateResult(-1, "re2"));
        taskDetailDO.setStatus("success");
        taskDetailDO.setResult("re2");
        Assertions.assertTrue(compareTaskDetailDO(taskDetailDO, taskDetailRepository.queryById(-1)));

        Assertions.assertEquals(1, taskDetailRepository.deleteById(-1));
    }

    /**
     * Description: 测试:根据instanceName返回原子任务列表
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskDetailQueryByInstanceName(){
        LoggerUtil.info(LOGGER, "testTaskDetailQueryByInstanceName() started...");

        List<TaskDetailDO> taskDetailDOS = new ArrayList<>();
        List<TaskDetailDO> taskDetailDOS_odd = new ArrayList<>();
        //构建数据
        for (int i = -1; i > -9; --i){
            taskDetailRepository.deleteById(i);
            TaskDetailDO taskDetailDO = new TaskDetailDO();
            taskDetailDO.setContent("con" + i);
            taskDetailDO.setExtInfo("ext");
            taskDetailDO.setId(i);
            taskDetailDO.setInstanceName("example_instant_name");
            taskDetailDO.setScript("scr" + i);
            taskDetailDO.setServiceName("service_name");
            taskDetailDO.setStatus("sta" + Math.abs(i) % 2);
            taskDetailDO.setResult("");
            taskDetailDOS.add(taskDetailDO);
            if (Math.abs(i) % 2 == 1){
                taskDetailDOS_odd.add(taskDetailDO);
            }
            Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));
        }

        //验证
        List<TaskDetailDO> actualTaskDetailDOS_1 = taskDetailRepository.queryByInstanceNameAndStatus("example_instant_name", "");
        //Assertions.assertTrue(compareTaskDetailDOList(actualTaskDetailDOS_1, taskDetailDOS));

        List<TaskDetailDO> actualTaskDetailDOS_2 = taskDetailRepository.queryByInstanceNameAndStatus("example_instant_name", null);
        //Assertions.assertTrue(compareTaskDetailDOList(actualTaskDetailDOS_2, taskDetailDOS));

        List<TaskDetailDO> actualTaskDetailDOS_odd = taskDetailRepository.queryByInstanceNameAndStatus("example_instant_name", "sta1");
        //Assertions.assertTrue(compareTaskDetailDOList(actualTaskDetailDOS_odd, taskDetailDOS_odd));

        for (int i = -1; i > -9; --i){
            Assertions.assertEquals(1, taskDetailRepository.deleteById(i));
        }
    }

    /**
     * Description: 俩原子任务比较
     *
     * @author blacksea3(jxt)
     * @date 2020/8/2
     * @param expected: 原
     * @param actual: 实际
     * @return boolean 结果
     */
    private boolean compareTaskDetailDO(TaskDetailDO expected, TaskDetailDO actual){
        if (expected == null){
            return actual == null;
        }else{
            if (actual == null){
                return false;
            }
            return (expected.getId().equals(actual.getId())) &&
                    (expected.getContent().equals(actual.getContent())) &&
                    (expected.getExtInfo().equals(actual.getExtInfo())) &&
                    (expected.getInstanceName().equals(actual.getInstanceName())) &&
                    (expected.getScript().equals(actual.getScript())) &&
                    (expected.getServiceName().equals(actual.getServiceName())) &&
                    (expected.getStatus().equals(actual.getStatus()));
        }
    }

    /**
     * Description: 俩原子任务比较, 不带ID
     *
     * @author blacksea3(jxt)
     * @date 2020/8/2
     * @param expected: 原
     * @param actual: 实际
     * @return boolean 结果
     */
    public boolean compareTaskDetailDOWithoutID(TaskDetailDO expected, TaskDetailDO actual){
        if (expected == null){
            return actual == null;
        }else{
            if (actual == null){
                return false;
            }
            return ((expected.getContent().equals(actual.getContent())) &&
                    (expected.getExtInfo().equals(actual.getExtInfo())) &&
                    (expected.getInstanceName().equals(actual.getInstanceName())) &&
                    (expected.getScript().equals(actual.getScript())) &&
                    (expected.getServiceName().equals(actual.getServiceName())) &&
                    (expected.getStatus().equals(actual.getStatus())));
        }
    }

    /**
     * Description: 俩原子任务列表比较, 顺序不定, O(N^2)
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 原列表
     * @param actual: 新列表
     * @return boolean 结果
     */
    private boolean compareTaskDetailDOList(List<TaskDetailDO> expected, List<TaskDetailDO> actual){
        if (expected == null){
            return actual == null;
        }else{
            if (expected.size() != actual.size()){
                return false;
            }

            for (TaskDetailDO taskDetailDO:expected){
                boolean isFound = false;
                for (TaskDetailDO taskDetailDO1:actual){
                    if (compareTaskDetailDO(taskDetailDO, taskDetailDO1)){
                        isFound = true;
                        break;
                    }
                }
                if (!isFound){
                    return false;
                }
            }
            return true;
        }
    }

}
