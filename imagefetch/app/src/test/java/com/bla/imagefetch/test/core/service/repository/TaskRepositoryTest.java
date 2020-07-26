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

import java.util.ArrayList;
import java.util.List;

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

        Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));
        taskDetailDO.setExtInfo("newext");
        Assertions.assertEquals(1, taskDetailRepository.update(taskDetailDO));
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
            taskDetailDOS.add(taskDetailDO);
            Assertions.assertEquals(1, (long) taskDetailRepository.insert(taskDetailDO));
        }

        //验证
        List<TaskDetailDO> actualTaskDetailDOS = taskDetailRepository.queryByInstanceName("example_instant_name");
        Assertions.assertTrue(compareTaskDetailDOList(actualTaskDetailDOS, taskDetailDOS));

        for (int i = -1; i > -9; --i){
            Assertions.assertEquals(1, taskDetailRepository.deleteById(i));
        }
    }

    /**
     * Description: 俩原子任务比较
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 原
     * @param actual: 实际
     * @return boolean 结果
     */
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
