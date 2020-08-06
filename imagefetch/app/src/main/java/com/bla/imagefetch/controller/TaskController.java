package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

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

    @RequestMapping(value = "uploadZipImages")
    public CommonResponseDTO uploadZipImages(@RequestParam("file") MultipartFile file){
        String fullDir = globalConstant.getImageDirectory() + "\\" + String.valueOf(new Date().getTime());

        String ret = FileUtil.saveFile(fullDir, file);
        LoggerUtil.info(LOGGER, "保存图片结果:", ret);

        String ret2 = FileUtil.extractZip(ret);
        LoggerUtil.info(LOGGER, "解压zip结果:", ret2);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setInfo("");
        commonResponseDTO.setSuccess(true);
        return commonResponseDTO;
    }
}
