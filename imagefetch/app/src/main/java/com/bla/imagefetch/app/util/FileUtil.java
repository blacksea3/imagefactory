package com.bla.imagefetch.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
public class FileUtil {

    private Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private enum PIC_SUFFIX{
        IMG("img"),
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
        for(PIC_SUFFIX pic : PIC_SUFFIX.values()){
            ret.add(pic.getName());
        }
        return ret;
    }
}
