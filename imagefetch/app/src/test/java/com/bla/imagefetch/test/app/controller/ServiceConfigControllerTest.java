package com.bla.imagefetch.test.app.controller;

import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.ServiceConfigController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

    private MockMvc mockMvc;

    @Test
    public void testAddServiceConfig() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceConfigController).build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addServiceConfig")
                .accept(MediaType.APPLICATION_JSON)
                .param("originContent", "15221365094"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        LoggerUtil.info(LOGGER, mvcResult.getResponse().getContentAsString());
    }

}
