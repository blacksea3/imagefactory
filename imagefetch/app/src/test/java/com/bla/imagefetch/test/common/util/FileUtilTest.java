package com.bla.imagefetch.test.common.util;

import com.bla.imagefetch.common.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName FileUtilTest 文件工具类测试
 * @see FileUtil
 *
 * @author blacksea3(jxt)
 * @date 2020/8/3 22:10
 */
@SpringBootTest
public class FileUtilTest {

    @Test
    void testFindAllPicFiles() {
        List<String> imageFiles = FileUtil.findAllPicFiles("images\\20200803");
        System.out.println(imageFiles);
    }

    @Test
    void testDownloadFromRemoteUrl(){
        String ret = FileUtil.downloadFromRemoteUrl(
                "http://vibktprfx-prod-prod-aic-gd-cn-shanghai.oss-cn-shanghai.aliyuncs.com/photo-style-imitation/84F9A7CC-B453-47B3-82AC-BCD7373FBFE6__e61020200804-080530.jpg?Expires=1596530131&OSSAccessKeyId=LTAI4FoLmvQ9urWXgSRpDvh1&Signature=LLIL84Ra0f0yvUGbeXmKL0QhACU%3D",
                "E:\\imageFactory\\images\\20200803",
                "result.jpg"
        );
        System.out.println(ret);
    }
}
