package com.bla.imagefetch.test.upper.service.topAlg.aliyun;

import com.aliyuncs.exceptions.ClientException;
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.GlobalConstant;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.test.BaseTest;
import com.bla.imagefetch.upper.service.VO.CommonAlgRequestVO;
import com.bla.imagefetch.upper.service.VO.CommonAlgResponseVO;
import com.bla.imagefetch.upper.service.topAlg.aliyun.CommonImageAlgInterface;
import com.bla.imagefetch.upper.service.topAlg.aliyun.ImageStyle;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ImageStyleTest 图片风格测试类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4 15:57
 */
@SpringBootTest
public class ImageStyleTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageStyleTest.class);

    @Resource(name = "imitatePhotoStyle")
    private CommonImageAlgInterface imitatePhotoStyle;

    @Resource(name = "recoverImage")
    private CommonImageAlgInterface recoverImage;

    @Autowired
    private GlobalConstant globalConstant;

    @Test
    public void testChangeImageStyle(){
        CommonAlgRequestVO commonAlgRequestVO = new CommonAlgRequestVO();
        Map<String, String> content = new HashMap<>();
        content.put("source", "E:\\imageFactory\\newImages\\source.jpg");
        content.put("ref", "E:\\imageFactory\\newImages\\ref.png");
        String resFileName = "E:\\imageFactory\\newImages\\res" + new Date().getTime() + ".jpg";
        content.put("res", resFileName);
        commonAlgRequestVO.setContent(content);
        CommonAlgResponseVO commonAlgResponseVO = imitatePhotoStyle.exec(commonAlgRequestVO);

        Assertions.assertTrue(commonAlgResponseVO.isSuccess());

        Map<String, String> ret = commonAlgResponseVO.getResult();

        Assertions.assertEquals(ret.get("info"), "正确");

        LoggerUtil.info(LOGGER, ret.get("detail"));
    }

    @Test
    public void testRecoverImage(){
        CommonAlgRequestVO commonAlgRequestVO = new CommonAlgRequestVO();
        Map<String, String> content = new HashMap<>();
        content.put("source", "E:\\imageFactory\\newImages\\recoverImage\\source.jpg");
        content.put("color", "FFFFFF");
        String resFileName = "E:\\imageFactory\\newImages\\recoverImage\\res" + new Date().getTime() + ".jpg";
        content.put("res", resFileName);
        commonAlgRequestVO.setContent(content);
        CommonAlgResponseVO commonAlgResponseVO = recoverImage.exec(commonAlgRequestVO);

        Assertions.assertTrue(commonAlgResponseVO.isSuccess());

        Map<String, String> ret = commonAlgResponseVO.getResult();

        Assertions.assertEquals(ret.get("info"), "正确");

        LoggerUtil.info(LOGGER, ret.get("detail"));
    }

}
