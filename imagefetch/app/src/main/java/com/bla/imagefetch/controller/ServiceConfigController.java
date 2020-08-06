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

    /**
     * Description: 添加服务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/6
     * @param serviceConfigDTO: 服务配置DTO
     * @return com.bla.imagefetch.controller.DTO.CommonResponseDTO 通用响应DTO
     */
    @RequestMapping(value = "addServiceConfig")
    public CommonResponseDTO addServiceConfig(@RequestBody ServiceConfigDTO serviceConfigDTO){
        LoggerUtil.trace(LOGGER, "POST url:/addServiceConfig, get", serviceConfigDTO.toString());
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        ServiceConfigDO serviceConfigDO = ServiceConfigDTO.DTOconvertToDO(serviceConfigDTO);
        if (null != serviceConfigRepository.insert(serviceConfigDO)){
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("插入成功, 插入数据为:" + ServiceConfigDTO.DOconvertToDTO(serviceConfigDO).toString());
        }else{
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("插入失败, 插入数据为:" + ServiceConfigDTO.DOconvertToDTO(serviceConfigDO).toString());
        }
        return commonResponseDTO;
    }

    /**
     * Description: 查询所有服务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/5

     * @return java.util.List<com.bla.imagefetch.controller.DTO.ServiceConfigDTO> 结果，全量
     */
    @RequestMapping(value = "queryAllServiceConfig")
    public List<ServiceConfigDTO> queryAllServiceConfig(){
        List<ServiceConfigDO> serviceConfigDOS = serviceConfigRepository.queryAll();

        List<ServiceConfigDTO> res = new ArrayList<>();
        for (ServiceConfigDO serviceConfigDO:serviceConfigDOS){
            res.add(ServiceConfigDTO.DOconvertToDTO(serviceConfigDO));
        }
        return res;
    }

    @RequestMapping(value = "updateServiceConfig")
    public CommonResponseDTO updateServiceConfig(@RequestBody ServiceConfigDTO serviceConfigDTO){
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();

        ServiceConfigDO serviceConfigDO = ServiceConfigDTO.DTOconvertToDO(serviceConfigDTO);
        Integer ret = serviceConfigRepository.update(serviceConfigDO);
        if (ret == null || ret != 1){
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("更新失败, 更新数据为:" + ServiceConfigDTO.DOconvertToDTO(serviceConfigDO).toString());
        }else{
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("更新成功, 更新数据为:" + ServiceConfigDTO.DOconvertToDTO(serviceConfigDO).toString());
        }
        return commonResponseDTO;
    }

}
