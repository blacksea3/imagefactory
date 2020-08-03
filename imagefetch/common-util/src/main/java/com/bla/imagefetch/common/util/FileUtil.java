package com.bla.imagefetch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private enum PIC_SUFFIX{
        PNG("png"),
        JPG("jpg"),
        JPEG("jpeg");

        private String name;
        private PIC_SUFFIX(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 查找所有的图片文件, 在指定目录
     * @author jiaxiantao(blacksea3)
     * @date 2020/7/23
     * @param directory 目录
     * @return java.util.List<java.lang.String>
     */
    public static List<String> findAllPicFiles(String directory){
        List<String> ret = new ArrayList<>();

        LoggerUtil.trace(LOGGER, System.getProperty("user.dir"));

        //previous Maybe xxx/imagefetch/app
        Path dir = new File(System.getProperty("user.dir")).toPath();

        Path rootDirPath = dir.getParent().getParent();
        LoggerUtil.trace(LOGGER, rootDirPath.toString() + "\\" + directory);

        File rootDir = new File(rootDirPath.toString() + "\\" + directory);
        File[] allFiles = rootDir.listFiles();

        if (allFiles == null){
            return ret;
        }

        for (File file:allFiles){
            String name = file.getName();

            boolean found = false;
            for(PIC_SUFFIX pic : PIC_SUFFIX.values()){
                if (name.endsWith(pic.getName())){
                    found = true;
                    break;
                }
            }

            if (found){
                ret.add(name);
            }
        }
        return ret;
    }
}
