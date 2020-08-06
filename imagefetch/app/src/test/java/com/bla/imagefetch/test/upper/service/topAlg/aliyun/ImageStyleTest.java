package com.bla.imagefetch.test.upper.service.topAlg.aliyun;

import com.aliyuncs.exceptions.ClientException;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.test.BaseTest;
import com.bla.imagefetch.upper.service.topAlg.aliyun.ImageStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * ImageStyleTest 图片风格测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4 15:57
 */
@SpringBootTest
public class ImageStyleTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageStyleTest.class);

    @Autowired
    private ImageStyle imageStyle;

    @Autowired
    private GlobalConstant globalConstant;

    @Test
    public void testChangeImageStyle() throws IOException, ClientException {
        String directory = globalConstant.getImageDirectory() + "\\" + "20200803";

        List<String> imageFiles = FileUtil.findAllPicFiles(directory);
        String refImageName = null;
        String sourceImageName = null;
        for (String image:imageFiles){
            if (image.endsWith("ref.png")){
                refImageName = image;
            }else{
                sourceImageName = image;
            }
        }

        String downloadUrl = imageStyle.changeImageStyle(sourceImageName, refImageName);
        Assertions.assertNotNull(downloadUrl);

        String ret = FileUtil.downloadFromRemoteUrl(downloadUrl, directory, "res.jpg");
        LoggerUtil.info(LOGGER, ret);
    }

}
