package com.bla.imagefetch.test.common.util;

import com.bla.imagefetch.common.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName FileUtilTest 文件工具类测试
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
}
