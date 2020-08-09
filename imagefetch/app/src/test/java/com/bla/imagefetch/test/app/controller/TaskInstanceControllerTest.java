package com.bla.imagefetch.test.app.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.controller.TaskInstanceController;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import com.bla.imagefetch.test.BaseTest;
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

import java.util.List;

/**
 * ClassName TaskControllerTest
 *
 * @author blacksea3(jxt)
 * @date 2020/8/8 19:55
 */
@SpringBootTest
public class TaskInstanceControllerTest extends BaseTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskInstanceControllerTest.class);

    @Autowired
    private TaskInstanceController taskInstanceController;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    private MockMvc mockMvc;

    @Test
    public void testQueryTaskInstance() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(taskInstanceController).build();

        //准备数据
        for (int i = 0; i < 4; ++i){
            TaskInstanceDO expected = new TaskInstanceDO();
            expected.setTotalNum(3);
            expected.setHandleNum(0);
            if (i == 0){
                expected.setStatus("init");
            }else if (i == 1){
                expected.setStatus("disable");
            }else if (i == 2){
                expected.setStatus("running");
            }else{
                expected.setStatus("finish");
            }

            expected.setPriority(1);
            expected.setDescription("");
            expected.setServiceName("example_service_config_name");
            expected.setConfigName("example_task_config_name");
            expected.setName("example_dir" + String.valueOf(i));

            {
                TaskInstanceDO temp = taskInstanceRepository.queryByName("example_dir" + String.valueOf(i));
                if (temp != null){
                    taskInstanceRepository.deleteById(temp.getId());
                }
            }
            Assertions.assertNotNull(taskInstanceRepository.insert(expected));
        }

        //Mock API
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/queryTaskInstance")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        //删除数据
        List<TaskInstanceDO> actual = taskInstanceRepository.queryAllTaskInstances();

        for (TaskInstanceDO right:actual){
            Assertions.assertEquals(1, taskInstanceRepository.deleteById(right.getId()));
        }
    }


}
