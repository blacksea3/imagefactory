package com.bla.imagefetch.test.core.service.repository;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName taskConfigRepositoryTest
 * 任务配置仓库 测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/7/26 22:52
 */
@SpringBootTest
public class TaskConfigRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskConfigRepositoryTest.class);

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    /**
     * Description: 简单测试:删除和单项带Id插入
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskConfigDeleteAndInsert() {
        LoggerUtil.info(LOGGER, "testTaskConfigDeleteAndInsert() started...");

        taskConfigRepository.deleteById(-2);
        Assertions.assertEquals(0, taskConfigRepository.deleteById(-2));
        Assertions.assertNull(taskConfigRepository.deleteById(null));

        Assertions.assertNull(taskConfigRepository.queryById(-2));
        Assertions.assertNull(taskConfigRepository.queryById(null));

        taskConfigRepository.deleteById(-1);
        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setDescription("des");
        taskConfigDO.setServiceName("sername");
        taskConfigDO.setExtInfo("ext");
        taskConfigDO.setId(-1);
        taskConfigDO.setName("na");
        taskConfigDO.setStatus("sta");

        Assertions.assertEquals(1, (long) taskConfigRepository.insert(taskConfigDO));

        Assertions.assertTrue(compareTaskConfigDO(taskConfigDO, taskConfigRepository.queryById(-1)));
        Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
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

        taskConfigRepository.deleteById(-1);
        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setDescription("des");
        taskConfigDO.setServiceName("sername");
        taskConfigDO.setExtInfo("ext");
        taskConfigDO.setId(-1);
        taskConfigDO.setName("na");
        taskConfigDO.setStatus("sta");

        Assertions.assertEquals(1, (long) taskConfigRepository.insert(taskConfigDO));
        taskConfigDO.setExtInfo("newext");
        Assertions.assertEquals(1, taskConfigRepository.update(taskConfigDO));
        Assertions.assertTrue(compareTaskConfigDO(taskConfigDO, taskConfigRepository.queryById(-1)));

        Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
    }

    /**
     * Description: 测试:根据name返回任务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     */
    @Test
    void testTaskConfigQueryByName(){
        LoggerUtil.info(LOGGER, "testTaskConfigQueryByName() started...");

        taskConfigRepository.deleteById(-1);
        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setDescription("des");
        taskConfigDO.setServiceName("sername");
        taskConfigDO.setExtInfo("ext");
        taskConfigDO.setId(-1);
        taskConfigDO.setName("na");
        taskConfigDO.setStatus("sta");
        Assertions.assertEquals(1, (long) taskConfigRepository.insert(taskConfigDO));

        Assertions.assertTrue(compareTaskConfigDO(taskConfigDO, taskConfigRepository.queryByName("na")));

        Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
    }

    /**
     * Description: 比较两个任务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/7/26
     * @param expected: 期待
     * @param actual: 实际
     * @return boolean 结果
     */
    private boolean compareTaskConfigDO(TaskConfigDO expected, TaskConfigDO actual){
        if (expected == null){
            return actual == null;
        }else{
            if (actual == null){
                return false;
            }
            return (expected.getId().equals(actual.getId())) &&
                    (expected.getDescription().equals(actual.getDescription())) &&
                    (expected.getExtInfo().equals(actual.getExtInfo())) &&
                    (expected.getServiceName().equals(actual.getServiceName())) &&
                    (expected.getName().equals(actual.getName())) &&
                    (expected.getStatus().equals(actual.getStatus()));
        }
    }

}
