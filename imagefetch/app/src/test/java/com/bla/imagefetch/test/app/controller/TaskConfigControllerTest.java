package com.bla.imagefetch.test.app.controller;

import com.alibaba.fastjson.JSON;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.controller.DTO.TaskConfigDTO;
import com.bla.imagefetch.controller.ServiceConfigController;
import com.bla.imagefetch.controller.TaskConfigController;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import com.bla.imagefetch.test.BaseTest;
import com.bla.imagefetch.test.core.service.repository.ServiceConfigRepositoryTest;
import com.bla.imagefetch.test.core.service.repository.TaskConfigRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * TaskConfigControllerTest 任务配置Controller测试
 *
 * @author blacksea3(jxt)
 * @date 2020/8/6 21:09
 */
@SpringBootTest
public class TaskConfigControllerTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConfigControllerTest.class);

    @Autowired
    private TaskConfigController taskConfigController;

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    private MockMvc mockMvc;

    @Test
    public void testAddServiceConfig() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(taskConfigController).build();

        TaskConfigDO old = taskConfigRepository.queryByName("example_task_config_name");
        if (old != null){
            Assertions.assertEquals(1, taskConfigRepository.deleteById(old.getId()));
        }

        TaskConfigDTO taskConfigDTO = new TaskConfigDTO();
        taskConfigDTO.setDescription("des");
        taskConfigDTO.setExtInfo("");
        taskConfigDTO.setName("example_task_config_name");
        taskConfigDTO.setServiceName("example_service_config_name");
        taskConfigDTO.setStatus("valid");

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/addTaskConfig")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(taskConfigDTO))
                );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        TaskConfigDO actual = taskConfigRepository.queryByName("example_task_config_name");

        Assertions.assertTrue(
                TaskConfigRepositoryTest.compareTaskConfigDOWithoutID(TaskConfigDTO.DTOconvertToDO(taskConfigDTO), actual));

        Assertions.assertEquals(1, taskConfigRepository.deleteById(actual.getId()));
    }

    @Test
    public void testQueryAllServiceConfig() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(taskConfigController).build();

        TaskConfigDO old = taskConfigRepository.queryByName("example_task_config_name");
        if (old != null){
            Assertions.assertEquals(1, taskConfigRepository.deleteById(old.getId()));
        }

        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setDescription("des");
        taskConfigDO.setExtInfo("");
        taskConfigDO.setName("example_task_config_name");
        taskConfigDO.setServiceName("example_service_config_name");
        taskConfigDO.setStatus("valid");

        Assertions.assertNotNull(taskConfigRepository.insert(taskConfigDO));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/queryAllTaskConfig")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(1, taskConfigRepository.deleteById(taskConfigDO.getId()));
    }

    @Test
    public void testUpdateServiceConfig() throws Exception{
        //准备数据
        mockMvc = MockMvcBuilders.standaloneSetup(taskConfigController).build();

        TaskConfigDO old = taskConfigRepository.queryByName("example_task_config_name");
        if (old != null){
            Assertions.assertEquals(1, taskConfigRepository.deleteById(old.getId()));
        }

        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setDescription("des");
        taskConfigDO.setExtInfo("");
        taskConfigDO.setName("example_task_config_name");
        taskConfigDO.setServiceName("example_service_config_name");
        taskConfigDO.setStatus("valid");

        Assertions.assertNotNull(taskConfigRepository.insert(taskConfigDO));

        TaskConfigDTO taskConfigDTO = TaskConfigDTO.DOconvertToDTO(taskConfigDO);
        taskConfigDTO.setDescription("dddes");

        taskConfigDO.setDescription("dddes");

        //获取结果
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/updateTaskConfig")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(taskConfigDTO))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        //结果判断
        TaskConfigDO actual = taskConfigRepository.queryByName("example_task_config_name");
        Assertions.assertTrue(
                TaskConfigRepositoryTest.compareTaskConfigDOWithoutID(taskConfigDO, actual));
        //清除数据
        Assertions.assertEquals(1, taskConfigRepository.deleteById(taskConfigDO.getId()));
    }

}
