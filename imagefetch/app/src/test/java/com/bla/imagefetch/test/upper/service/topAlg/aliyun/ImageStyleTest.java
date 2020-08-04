package com.bla.imagefetch.test.upper.service.topAlg.aliyun;

import com.aliyuncs.exceptions.ClientException;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.upper.service.topAlg.aliyun.ImageStyle;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
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
public class ImageStyleTest {

    @Autowired
    private ImageStyle imageStyle;

    @Test
    public void testChangeImageStyle() throws IOException, ClientException {
        List<String> imageFiles = FileUtil.findAllPicFiles("images\\20200803");
        String refImageName = null;
        String sourceImageName = null;
        for (String image:imageFiles){
            if (image.endsWith("ref.png")){
                refImageName = image;
            }else{
                sourceImageName = image;
            }
        }

        imageStyle.changeImageStyle(sourceImageName, refImageName);
    }

}
