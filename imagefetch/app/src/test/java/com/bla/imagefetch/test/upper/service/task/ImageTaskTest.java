package com.bla.imagefetch.test.upper.service.task;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.core.service.repository.*;
import com.bla.imagefetch.test.core.service.repository.ServiceConfigRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskConfigRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskDetailRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskInstanceRepositoryTest;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;
import com.bla.imagefetch.upper.service.VO.VOConvertToDO;
import com.bla.imagefetch.upper.service.task.ImageTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    /**
     *
     * @author blacksea3(jxt)
     * @date 2020/8/2

     * @return void
     */
    @Test
    void testAddServiceConfig(){
        ServiceConfigVO serviceConfigVO = new ServiceConfigVO();
        serviceConfigVO.setBeanName("bn");
        serviceConfigVO.setBeanType(ServiceConfigVO.ENUM_SERVICE_CONFIG_VO_BEAN_TYPE.LOCAL);
        serviceConfigVO.setName("example_service_name");
        serviceConfigVO.setSysName("sa");

        Integer ret = imageTask.addServiceConfig(serviceConfigVO);
        ServiceConfigDO actual = serviceConfigRepository.queryById(ret);
        ServiceConfigDO expected = VOConvertToDO.VOServiceConfigDO(serviceConfigVO);
        expected.setId(ret);

        ServiceConfigRepositoryTest serviceConfigRepositoryTest = new ServiceConfigRepositoryTest();
        Assertions.assertTrue(serviceConfigRepositoryTest.compareServiceConfigDO(actual, expected));

        Assertions.assertEquals(1, serviceConfigRepository.deleteById(ret));
    }

    @Test
    void testAddTaskConfig(){
        TaskConfigVO taskConfigVO = new TaskConfigVO();
        taskConfigVO.setDescription("");
        taskConfigVO.setName("example_task_config_name");
        taskConfigVO.setServiceName("example_service_name");
        taskConfigVO.setStatus("valid");

        Integer ret = imageTask.addTaskConfig(taskConfigVO);
        TaskConfigDO actual = taskConfigRepository.queryById(ret);
        TaskConfigDO expected = VOConvertToDO.VOTaskConfigDO(taskConfigVO);
        expected.setId(ret);

        TaskConfigRepositoryTest taskConfigRepositoryTest = new TaskConfigRepositoryTest();
        Assertions.assertTrue(taskConfigRepositoryTest.compareTaskConfigDO(actual, expected));

        Assertions.assertEquals(1, taskConfigRepository.deleteById(ret));
    }

    @Test
    void testAddTasks(){
        {
            //taskConfig不存在
            List<String> files = new ArrayList<>();
            files.add("test_filename_1");
            files.add("test_filename_2");

            serviceConfigRepository.deleteById(-1);
            ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
            serviceConfigDO.setBeanName("bn");
            serviceConfigDO.setBeanType("bt");
            serviceConfigDO.setExtInfo("ext");
            serviceConfigDO.setId(-1);
            serviceConfigDO.setName("example_service_config_name");
            serviceConfigDO.setSysName("sys_name");
            Assertions.assertEquals(1, (long) serviceConfigRepository.insert(serviceConfigDO));

            TaskInstanceAndDetailVO taskInstanceAndDetailVO = new TaskInstanceAndDetailVO();
            taskInstanceAndDetailVO.setConfigName("example_task_config_name");
            taskInstanceAndDetailVO.setServiceName("example_service_name");
            taskInstanceAndDetailVO.setDirectory("../images/20200803");

            Assertions.assertThrows(RuntimeException.class,
                    () -> {
                imageTask.addTasks(taskInstanceAndDetailVO);
            }
            );

            Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));
        }

        {
            //serviceConfig不存在
            List<String> files = new ArrayList<>();
            files.add("test_filename_1");
            files.add("test_filename_2");

            taskConfigRepository.deleteById(-1);
            TaskConfigDO taskConfigDO = new TaskConfigDO();
            taskConfigDO.setDescription("des");
            taskConfigDO.setServiceName("sername");
            taskConfigDO.setExtInfo("ext");
            taskConfigDO.setId(-1);
            taskConfigDO.setName("example_task_config_name");
            taskConfigDO.setStatus("sta");
            Assertions.assertEquals(1, (long) taskConfigRepository.insert(taskConfigDO));

            TaskInstanceAndDetailVO taskInstanceAndDetailVO = new TaskInstanceAndDetailVO();
            taskInstanceAndDetailVO.setConfigName("example_task_config_name");
            taskInstanceAndDetailVO.setServiceName("example_service_name");
            taskInstanceAndDetailVO.setDirectory("../images/20200803");

            Assertions.assertThrows(RuntimeException.class,
                    () -> {
                        imageTask.addTasks(taskInstanceAndDetailVO);
                    }
            );

            Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
        }

        {
            //内部异常: insertTaskInstanceAndTaskDetailForImages异常
            TaskInstanceAndDetailVO taskInstanceAndDetailVO = new TaskInstanceAndDetailVO();
            taskInstanceAndDetailVO.setConfigName("example_task_config_name");
            taskInstanceAndDetailVO.setServiceName("example_service_name");
            taskInstanceAndDetailVO.setDirectory(null);

            Assertions.assertThrows(RuntimeException.class,
                    () -> {
                        imageTask.addTasks(taskInstanceAndDetailVO);
                    }
            );
        }

        {
            //正常

            //数据准备
            List<String> files = new ArrayList<>();
            files.add("test_filename_1");
            files.add("test_filename_2");

            //手动插入配置数据
            taskConfigRepository.deleteById(-1);
            TaskConfigDO taskConfigDO = new TaskConfigDO();
            taskConfigDO.setDescription("des");
            taskConfigDO.setServiceName("sername");
            taskConfigDO.setExtInfo("ext");
            taskConfigDO.setId(-1);
            taskConfigDO.setName("example_task_config_name");
            taskConfigDO.setStatus("sta");
            Assertions.assertEquals(1, (long) taskConfigRepository.insert(taskConfigDO));

            serviceConfigRepository.deleteById(-1);
            ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
            serviceConfigDO.setBeanName("bn");
            serviceConfigDO.setBeanType("bt");
            serviceConfigDO.setExtInfo("ext");
            serviceConfigDO.setId(-1);
            serviceConfigDO.setName("example_service_config_name");
            serviceConfigDO.setSysName("sys_name");
            Assertions.assertEquals(1, (long) serviceConfigRepository.insert(serviceConfigDO));

            //执行
            TaskInstanceAndDetailVO taskInstanceAndDetailVO = new TaskInstanceAndDetailVO();
            taskInstanceAndDetailVO.setConfigName("example_task_config_name");
            taskInstanceAndDetailVO.setServiceName("example_service_config_name");
            taskInstanceAndDetailVO.setDirectory("example_dir");

            imageTask.addTasks(taskInstanceAndDetailVO);

            //数据检查
            TaskInstanceDO expected = new TaskInstanceDO();
            expected.setTotalNum(files.size());
            expected.setHandleNum(0);
            expected.setStatus("init");
            expected.setPriority(1);
            expected.setDescription("");
            expected.setServiceName("example_service_config_name");
            expected.setConfigName("example_task_config_name");
            expected.setName("example_dir");

            TaskInstanceRepositoryTest taskInstanceRepositoryTest = new TaskInstanceRepositoryTest();
            TaskInstanceDO actual = taskInstanceRepository.queryByName("example_dir");
            Assertions.assertTrue(taskInstanceRepositoryTest.compareTaskInstanceDOWithoutID(expected, actual));
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

            //删除数据
            Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));

            Assertions.assertEquals(1, taskInstanceRepository.deleteById(actual.getId()));
            Assertions.assertEquals(1, taskDetailRepository.deleteById(actuals.get(0).getId()));
            Assertions.assertEquals(1, taskDetailRepository.deleteById(actuals.get(1).getId()));
        }
    }

}
