package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonIntegerRequestDTO;
import com.bla.imagefetch.controller.DTO.CommonStringRequestDTO;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.TaskInstanceDTO;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 任务 Controller
 *
 * @author blacksea3(jxt)
 * @date 2020/8/6 22:11
 */
@RestController
public class TaskInstanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskInstanceController.class);

    @Autowired
    private GlobalConstant globalConstant;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @RequestMapping(value = "uploadZipImages", method = RequestMethod.POST)
    public CommonResponseDTO uploadZipImages(@RequestParam("file") MultipartFile file){
        String fullDir = globalConstant.getImageDirectory() + "\\" + String.valueOf(new Date().getTime());

        String ret = FileUtil.saveFile(fullDir, file);
        if (ret == null || ret.equals("")){
            LoggerUtil.error(LOGGER, "保存压缩包失败:", ret);
            CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
            commonResponseDTO.setInfo("保存压缩包失败:" + ret);
            commonResponseDTO.setSuccess(false);
            return commonResponseDTO;
        }
        LoggerUtil.info(LOGGER, "保存压缩包结果:", ret);

        String ret2 = FileUtil.extractZip(ret);
        LoggerUtil.info(LOGGER, "解压zip结果:", ret2);
        if (ret2 == null || ret2.equals("")){
            LoggerUtil.error(LOGGER, "解压zip结果:", ret2);
            CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
            commonResponseDTO.setInfo("解压zip结果:" + ret2);
            commonResponseDTO.setSuccess(false);
            return commonResponseDTO;
        }

        List<String> files = FileUtil.findAllPicFiles(ret2);

        boolean dualWriteRes = taskInstanceRepository.insertTaskInstanceAndTaskDetailForImages(
                ret2, files, "imageStyle", "test"
        );

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        if (dualWriteRes){
            commonResponseDTO.setInfo("");
            commonResponseDTO.setSuccess(true);
        }else{
            commonResponseDTO.setInfo("双写失败");
            commonResponseDTO.setSuccess(false);
        }
        return commonResponseDTO;
    }

    /**
     * Description: 查询所有任务实例
     *
     * @author blacksea3(jxt)
     * @date 2020/8/8

     * @return java.util.List<com.bla.imagefetch.controller.DTO.TaskInstanceDTO>
     */
    @RequestMapping(value = "queryTaskInstance", method = RequestMethod.POST)
    public List<TaskInstanceDTO> queryTaskInstance(){
        List<TaskInstanceDTO> ret = new ArrayList<>();

        List<TaskInstanceDO> raw = taskInstanceRepository.queryAllTaskInstances();
        for (TaskInstanceDO temp:raw){
            ret.add(TaskInstanceDTO.DOconvertToDTO(temp));
        }

        return ret;
    }

    /**
     * Description: 激活任务实例
     *
     * @author blacksea3(jxt)
     * @date 2020/8/9
     * @param id: 任务实例ID
     * @return com.bla.imagefetch.controller.DTO.CommonResponseDTO
     */
    @RequestMapping(value = "enableTaskInstance", method = RequestMethod.POST)
    public CommonResponseDTO enableTaskInstance(@RequestBody CommonIntegerRequestDTO id){
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        Integer ret = taskInstanceRepository.enableTaskInstance(id.getNum());
        if (ret == null || ret != 1){
            commonResponseDTO.setSuccess(false);
            commonResponseDTO.setInfo("更新状态失败");
        }else{
            commonResponseDTO.setSuccess(true);
            commonResponseDTO.setInfo("");
        }
        return commonResponseDTO;
    }
}
