package com.bla.imagefetch.test.app.controller;

import com.alibaba.fastjson.JSON;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.ServiceConfigDTO;
import com.bla.imagefetch.controller.ServiceConfigController;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import com.bla.imagefetch.test.core.service.repository.ServiceConfigRepositoryTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;

/**
 * ServiceConfigControllerTest 服务配置Controller测试
 *
 * @author blacksea3(jxt)
 * @date 2020/8/5 23:27
 */
@SpringBootTest
public class ServiceConfigControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfigControllerTest.class);

    @Autowired
    private ServiceConfigController serviceConfigController;

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    private MockMvc mockMvc;

    @Test
    public void testAddServiceConfig() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceConfigController).build();

        ServiceConfigDO old = serviceConfigRepository.queryByName("example_service_config_name");
        if (old != null){
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(old.getId()));
        }

        ServiceConfigDTO serviceConfigDTO = new ServiceConfigDTO();
        serviceConfigDTO.setBeanName("bn");
        serviceConfigDTO.setBeanType("bt");
        serviceConfigDTO.setExtInfo("");
        serviceConfigDTO.setName("example_service_config_name");
        serviceConfigDTO.setSysName("sysna");

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/addServiceConfig")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(serviceConfigDTO))
                );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        ServiceConfigDO actual = serviceConfigRepository.queryByName("example_service_config_name");

        Assertions.assertTrue(
                ServiceConfigRepositoryTest.compareServiceConfigDOWithoutID(ServiceConfigDTO.DTOconvertToDO(serviceConfigDTO), actual));

        Assertions.assertEquals(1, serviceConfigRepository.deleteById(actual.getId()));
    }

    @Test
    public void testQueryAllServiceConfig() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(serviceConfigController).build();

        ServiceConfigDO old = serviceConfigRepository.queryByName("example_service_config_name");
        if (old != null){
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(old.getId()));
        }

        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setBeanName("bn");
        serviceConfigDO.setBeanType("bt");
        serviceConfigDO.setExtInfo("");
        serviceConfigDO.setName("example_service_config_name");
        serviceConfigDO.setSysName("sysna");

        Assertions.assertNotNull(serviceConfigRepository.insert(serviceConfigDO));

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/queryAllServiceConfig")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(1, serviceConfigRepository.deleteById(serviceConfigDO.getId()));
    }

    @Test
    public void testUpdateServiceConfig() throws Exception{
        //准备数据
        mockMvc = MockMvcBuilders.standaloneSetup(serviceConfigController).build();

        ServiceConfigDO old = serviceConfigRepository.queryByName("example_service_config_name");
        if (old != null){
            Assertions.assertEquals(1, serviceConfigRepository.deleteById(old.getId()));
        }

        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setId(old.getId());
        serviceConfigDO.setBeanName("bn");
        serviceConfigDO.setBeanType("bt");
        serviceConfigDO.setExtInfo("");
        serviceConfigDO.setName("example_service_config_name");
        serviceConfigDO.setSysName("sysna");

        Assertions.assertNotNull(serviceConfigRepository.insert(serviceConfigDO));

        ServiceConfigDTO serviceConfigDTO = ServiceConfigDTO.DOconvertToDTO(serviceConfigDO);
        serviceConfigDTO.setSysName("syssys");

        serviceConfigDO.setSysName("syssys");

        //获取结果
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/updateServiceConfig")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(serviceConfigDTO))
        );

        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        //断言
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());

        //结果判断
        ServiceConfigDO actual = serviceConfigRepository.queryByName("example_service_config_name");
        Assertions.assertTrue(
                ServiceConfigRepositoryTest.compareServiceConfigDOWithoutID(serviceConfigDO, actual));
        //清除数据
        Assertions.assertEquals(1, serviceConfigRepository.deleteById(serviceConfigDO.getId()));
    }

}
