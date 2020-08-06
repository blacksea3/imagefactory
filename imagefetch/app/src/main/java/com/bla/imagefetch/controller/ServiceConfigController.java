package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.ServiceConfigDTO;
import com.bla.imagefetch.core.service.repository.ServiceConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ServiceConfigController 服务配置Controller
 *
 * @author blacksea3(jxt)
 * @date 2020/8/5 23:10
 */
@RestController
public class ServiceConfigController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceConfigController.class);

    @Autowired
    private ServiceConfigRepository serviceConfigRepository;

    @RequestMapping(value = "addServiceConfig")
    public CommonResponseDTO addServiceConfig(@RequestBody ServiceConfigDTO serviceConfigDTO){
        LoggerUtil.info(LOGGER, "POST url:/addServiceConfig, get", serviceConfigDTO.toString());
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        ServiceConfigDO serviceConfigDO = ServiceConfigDTO.DTOconvertToDO(serviceConfigDTO);
        if (null != serviceConfigRepository.insert(serviceConfigDO)){
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("插入成功, 插入数据为:" + serviceConfigDO.toString());
        }else{
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("插入失败, 待插入数据为:" + serviceConfigDTO.toString());
        }
        return commonResponseDTO;
    }

    /**
     * Description: 查询所有服务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/5

     * @return java.util.List<com.bla.imagefetch.controller.DTO.ServiceConfigDTO>
     */
    @RequestMapping(value = "queryAllServiceConfig")
    public List<ServiceConfigDTO> queryAllServiceConfig(){
        //TODO:待数据库查询
        return new ArrayList<>();
    }

    @RequestMapping(value = "updateServiceConfig")
    public CommonResponseDTO updateServiceConfig(ServiceConfigDTO serviceConfigDTO){
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        if (serviceConfigDTO.getId() == null){
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("Id为空");
        }else{
            //TODO:待数据库更新
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("");
        }
        return commonResponseDTO;
    }

}
