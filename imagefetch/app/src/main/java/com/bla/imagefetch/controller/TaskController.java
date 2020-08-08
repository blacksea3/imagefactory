package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.TaskInstanceDTO;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private GlobalConstant globalConstant;

    @Autowired
    private TaskInstanceRepository taskInstanceRepository;

    @RequestMapping(value = "uploadZipImages")
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

    @RequestMapping(value = "queryTaskInstance")
    public List<TaskInstanceDTO> queryTaskInstance(){
        List<TaskInstanceDTO> ret = new ArrayList<>();

        //TODO:查询所有的 in 状态, 不限数量 任务实例

        return ret;
    }
}
