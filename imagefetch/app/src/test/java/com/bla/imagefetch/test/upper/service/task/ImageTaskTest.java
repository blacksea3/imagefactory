package com.bla.imagefetch.test.upper.service.task;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.core.service.repository.*;
import com.bla.imagefetch.test.core.service.repository.ServiceConfigRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskConfigRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskDetailRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskInstanceRepositoryTest;
import com.bla.imagefetch.upper.service.VO.ServiceConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskConfigVO;
import com.bla.imagefetch.upper.service.VO.TaskInstanceAndDetailVO;
import com.bla.imagefetch.upper.service.VO.VOConvertToDO;
import com.bla.imagefetch.upper.service.quartz.MainJobDetailBean;
import com.bla.imagefetch.upper.service.task.AbstractTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    private AbstractTask abstractTask;

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    @Autowired
    private MainJobDetailBean mainJobDetailBean;

    @Autowired
    private GlobalConstant globalConstant;

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

        Integer ret = abstractTask.addServiceConfig(serviceConfigVO);
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

        Integer ret = abstractTask.addTaskConfig(taskConfigVO);
        TaskConfigDO actual = taskConfigRepository.queryById(ret);
        TaskConfigDO expected = VOConvertToDO.VOTaskConfigDO(taskConfigVO);
        expected.setId(ret);

        TaskConfigRepositoryTest taskConfigRepositoryTest = new TaskConfigRepositoryTest();
        Assertions.assertTrue(taskConfigRepositoryTest.compareTaskConfigDO(actual, expected));

        Assertions.assertEquals(1, taskConfigRepository.deleteById(ret));
    }

    /**
     * Description: 请注意:测试这个方法时，手动设置磁盘文件
     *
     * @author blacksea3(jxt)
     * @date 2020/8/3
     */

    @Test
    void testAddTasks(){
        {
            //taskConfig不存在

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
                abstractTask.addTasks(taskInstanceAndDetailVO);
            }
            );

            Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));
        }

        {
            //serviceConfig不存在
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
            taskInstanceAndDetailVO.setDirectory("images\\20200803");

            Assertions.assertThrows(RuntimeException.class,
                    () -> {
                        abstractTask.addTasks(taskInstanceAndDetailVO);
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
                        abstractTask.addTasks(taskInstanceAndDetailVO);
                    }
            );
        }

        {
            //正常

            //数据准备
            List<String> files = FileUtil.findAllPicFiles(globalConstant.getImageDirectory() + "\\" + "20200803");

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
            taskInstanceAndDetailVO.setDirectory("images\\20200803");

            abstractTask.addTasks(taskInstanceAndDetailVO);

            //数据检查
            TaskInstanceDO expected = new TaskInstanceDO();
            expected.setTotalNum(files.size());
            expected.setHandleNum(0);
            expected.setStatus("init");
            expected.setPriority(1);
            expected.setDescription("");
            expected.setServiceName("example_service_config_name");
            expected.setConfigName("example_task_config_name");
            expected.setName("20200803");

            TaskInstanceRepositoryTest taskInstanceRepositoryTest = new TaskInstanceRepositoryTest();
            TaskInstanceDO actual = taskInstanceRepository.queryByName("20200803");
            Assertions.assertTrue(taskInstanceRepositoryTest.compareTaskInstanceDOWithoutID(expected, actual));
            TaskDetailRepositoryTest taskDetailRepositoryTest = new TaskDetailRepositoryTest();
            List<TaskDetailDO> actuals = taskDetailRepository.queryByInstanceNameAndStatus("20200803", null);
            Assertions.assertEquals(files.size(), actuals.size());

            for (String filename:files){
                TaskDetailDO taskDetailDO = new TaskDetailDO();
                taskDetailDO.setStatus(TaskDetailRepositoryImpl.taskDetailStatus.INIT.get_val());
                taskDetailDO.setScript("");
                taskDetailDO.setExtInfo("");
                taskDetailDO.setServiceName("example_service_config_name");
                taskDetailDO.setInstanceName("20200803");
                taskDetailDO.setContent(filename);

                boolean findCompared = false;
                for (TaskDetailDO taskDetailDO1:actuals){
                    findCompared |= taskDetailRepositoryTest.compareTaskDetailDOWithoutID(taskDetailDO, taskDetailDO1);
                }

                Assertions.assertTrue(findCompared);
            }

            //删除数据
            Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));

            Assertions.assertEquals(1, taskInstanceRepository.deleteById(actual.getId()));
            for (TaskDetailDO iterActuals:actuals){
                Assertions.assertEquals(1, taskDetailRepository.deleteById(iterActuals.getId()));
            }
        }
    }

    @Test
    void testExecuteTask(){
        {
            //正常

            //数据准备
            List<String> files = FileUtil.findAllPicFiles(globalConstant.getImageDirectory() + "\\" + "20200803");

            //预删除数据
            taskConfigRepository.deleteById(-1);
            serviceConfigRepository.deleteById(-1);
            List<TaskDetailDO> rawActuals = taskDetailRepository.queryByInstanceNameAndStatus("20200803", null);
            TaskInstanceDO rawActual = taskInstanceRepository.queryByName("20200803");
            if (rawActual != null){
                taskInstanceRepository.deleteById(rawActual.getId());
            }
            if (rawActuals != null){
                for (TaskDetailDO iterRawActuals:rawActuals){
                    taskDetailRepository.deleteById(iterRawActuals.getId());
                }
            }


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
            serviceConfigDO.setBeanName("imageStyle");
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
            taskInstanceAndDetailVO.setDirectory("20200803");

            abstractTask.addTasks(taskInstanceAndDetailVO);

            //数据检查
            TaskInstanceDO expected = new TaskInstanceDO();
            expected.setTotalNum(files.size());
            expected.setHandleNum(0);
            expected.setStatus("init");
            expected.setPriority(1);
            expected.setDescription("");
            expected.setServiceName("example_service_config_name");
            expected.setConfigName("example_task_config_name");
            expected.setName("20200803");

            TaskInstanceRepositoryTest taskInstanceRepositoryTest = new TaskInstanceRepositoryTest();
            TaskInstanceDO actual = taskInstanceRepository.queryByName("20200803");
            Assertions.assertTrue(taskInstanceRepositoryTest.compareTaskInstanceDOWithoutID(expected, actual));
            TaskDetailRepositoryTest taskDetailRepositoryTest = new TaskDetailRepositoryTest();
            List<TaskDetailDO> actuals = taskDetailRepository.queryByInstanceNameAndStatus("20200803", null);
            Assertions.assertEquals(files.size(), actuals.size());

            for (String filename:files){
                TaskDetailDO taskDetailDO = new TaskDetailDO();
                taskDetailDO.setStatus(TaskDetailRepositoryImpl.taskDetailStatus.INIT.get_val());
                taskDetailDO.setScript("");
                taskDetailDO.setExtInfo("");
                taskDetailDO.setServiceName("example_service_config_name");
                taskDetailDO.setInstanceName("20200803");
                taskDetailDO.setContent(filename);

                boolean findCompared = false;
                for (TaskDetailDO taskDetailDO1:actuals){
                    findCompared |= taskDetailRepositoryTest.compareTaskDetailDOWithoutID(taskDetailDO, taskDetailDO1);
                }

                Assertions.assertTrue(findCompared);
            }

            //启动定时器
            try {
                mainJobDetailBean.startScheduler();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

            //等待10s
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //删除数据
            Assertions.assertEquals(1, taskConfigRepository.deleteById(-1));
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(-1));

            Assertions.assertEquals(1, taskInstanceRepository.deleteById(actual.getId()));
            for (TaskDetailDO iterActuals:actuals){
                Assertions.assertEquals(1, taskDetailRepository.deleteById(iterActuals.getId()));
            }
        }
    }

}
