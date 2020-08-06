package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.ServiceConfigDTO;
import com.bla.imagefetch.controller.DTO.TaskConfigDTO;
import com.bla.imagefetch.core.service.repository.TaskConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务配置 Controller
 *
 * @author blacksea3(jxt)
 * @date 2020/8/6 21:01
 */
@RestController
public class TaskConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskConfigController.class);

    @Autowired
    private TaskConfigRepository taskConfigRepository;

    /**
     * Description: 添加任务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/6
     * @param taskConfigDTO: 任务配置DTO
     * @return com.bla.imagefetch.controller.DTO.CommonResponseDTO 通用响应DTO
     */
    @RequestMapping(value = "addTaskConfig")
    public CommonResponseDTO addTaskConfig(@RequestBody TaskConfigDTO taskConfigDTO){
        LoggerUtil.trace(LOGGER, "POST url:/addTaskConfig, get", taskConfigDTO.toString());
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        TaskConfigDO taskConfigDO = TaskConfigDTO.DTOconvertToDO(taskConfigDTO);
        if (null != taskConfigRepository.insert(taskConfigDO)){
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("插入成功, 插入数据为:" + TaskConfigDTO.DOconvertToDTO(taskConfigDO).toString());
        }else{
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("插入失败, 插入数据为:" + TaskConfigDTO.DOconvertToDTO(taskConfigDO).toString());
        }
        return commonResponseDTO;
    }

    /**
     * Description: 查询所有任务配置
     *
     * @author blacksea3(jxt)
     * @date 2020/8/6

     * @return java.util.List<com.bla.imagefetch.controller.DTO.ServiceConfigDTO> 结果，全量
     */
    @RequestMapping(value = "queryAllTaskConfig")
    public List<TaskConfigDTO> queryAllTaskConfig(){
        List<TaskConfigDO> taskConfigDOS = taskConfigRepository.queryAll();

        List<TaskConfigDTO> res = new ArrayList<>();
        for (TaskConfigDO taskConfigDO:taskConfigDOS){
            res.add(TaskConfigDTO.DOconvertToDTO(taskConfigDO));
        }
        return res;
    }

    @RequestMapping(value = "updateTaskConfig")
    public CommonResponseDTO updateTaskConfig(@RequestBody TaskConfigDTO taskConfigDTO){
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();

        TaskConfigDO taskConfigDO = TaskConfigDTO.DTOconvertToDO(taskConfigDTO);
        Integer ret = taskConfigRepository.update(taskConfigDO);
        if (ret == null || ret != 1){
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("更新失败, 更新数据为:" + TaskConfigDTO.DOconvertToDTO(taskConfigDO).toString());
        }else{
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("更新成功, 更新数据为:" + TaskConfigDTO.DOconvertToDTO(taskConfigDO).toString());
        }
        return commonResponseDTO;
    }

}
