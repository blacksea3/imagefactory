package com.bla.imagefetch.controller;

import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.ServiceConfigDTO;
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

    @RequestMapping(value = "addServiceConfig")
    public CommonResponseDTO addServiceConfig(ServiceConfigDTO serviceConfigDTO){
        //TODO:待数据库插入数据
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setSuccess(true);
        commonResponseDTO.setInfo("");
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
