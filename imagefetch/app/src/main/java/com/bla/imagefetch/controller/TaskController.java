package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.controller.DTO.CommonResponseDTO;
import com.bla.imagefetch.controller.DTO.ImagePathDTO;
import com.bla.imagefetch.controller.DTO.TaskInstanceDTO;
import com.bla.imagefetch.core.service.repository.TaskInstanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
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

        //TODO:查询所有的 in 状态, 不限数量 任务实例
        List<TaskInstanceDO> raw = taskInstanceRepository.queryAllTaskInstances();
        for (TaskInstanceDO temp:raw){
            ret.add(TaskInstanceDTO.DOconvertToDTO(temp));
        }

        return ret;
    }

    /**
     * Description: 查询所有图片路径
     *
     * @author blacksea3(jxt)
     * @date 2020/8/8
     * @param image: 路径
     * @return java.util.List<java.lang.String>
     */
    @RequestMapping(value = "queryImageFileNames", method = RequestMethod.POST)
    public List<ImagePathDTO> queryImageFileNames(@RequestBody ImagePathDTO image) throws UnsupportedEncodingException {
        String realPath = URLDecoder.decode(image.getPath(), "UTF-8"); //decode
        List<ImagePathDTO> ret = new ArrayList<>();
        List<String> stringRet = FileUtil.findAllPicFiles(realPath);
        for (String temp:stringRet){
            ImagePathDTO imagePathDTO = new ImagePathDTO();
            imagePathDTO.setPath(temp);
            ret.add(imagePathDTO);
        }
        return ret;
    }

    /**
     * Description: 获取图片
     * 参考:https://blog.csdn.net/qq_18298439/article/details/89315478
     *
     * @author blacksea3(jxt)
     * @date 2020/8/8
     * @param fileName: 图片名
     * @return byte[] 图片内容
     */
    @RequestMapping(value = "queryImageContent", produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public byte[] queryImageContent(@RequestParam(name = "fileName") String fileName) throws IOException {
        String realPath = URLDecoder.decode(fileName, "UTF-8"); //decode

        File file = new File(realPath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}
