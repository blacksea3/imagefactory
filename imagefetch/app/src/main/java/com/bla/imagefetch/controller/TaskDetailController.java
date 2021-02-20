package com.bla.imagefetch.controller;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;
import com.bla.imagefetch.controller.DTO.CommonStringRequestDTO;
import com.bla.imagefetch.controller.DTO.TaskDetailDTO;
import com.bla.imagefetch.core.service.repository.TaskDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName TaskDetailController 原子任务 controller
 *
 * @author blacksea3(jxt)
 * @date 2020/8/9 10:05
 */
@RestController
public class TaskDetailController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskDetailController.class);

    @Autowired
    private TaskDetailRepository taskDetailRepository;

    /**
     * Description: 查询原子任务
     *
     * @author blacksea3(jxt)
     * @date 2020/8/9
     * @param requestDTO: 任务实例名
     * @return java.util.List<com.bla.imagefetch.controller.DTO.TaskDetailDTO>
     */
    @RequestMapping(value = "queryTaskDetail", method = RequestMethod.POST)
    public List<TaskDetailDTO> queryTaskDetail(@RequestBody CommonStringRequestDTO requestDTO) throws UnsupportedEncodingException {
        String realName = URLDecoder.decode(requestDTO.getInfo(), "UTF-8"); //decode
        List<TaskDetailDO> raw = taskDetailRepository.queryByInstanceNameAndStatus(realName, null);

        List<TaskDetailDTO> res = new ArrayList<>();
        for (TaskDetailDO taskDetailDO:raw){
            res.add(TaskDetailDTO.DOConvertToDTO(taskDetailDO));
        }
        return res;
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
