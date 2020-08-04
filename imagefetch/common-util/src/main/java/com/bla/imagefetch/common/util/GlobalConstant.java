package com.bla.imagefetch.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 全局:常量类, 单例
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4 21:42
 */
@Component
public class GlobalConstant implements InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalConstant.class);

    @Value("${imageDirectory}")
    private String imageDirectory;

    @Value("${imageTaskCron}")
    private String imageTaskCron;

    public String getImageDirectory() {
        return imageDirectory;
    }

    public String getImageTaskCron() {
        return imageTaskCron;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (imageDirectory == null){
            throw new Exception("请在配置文件设置imageDirectory,这将作为图片数据根文件夹");
        }else{
            LoggerUtil.info(LOGGER, "Bean of GlobalConstant has been initialized, " +
                    "imageDirectory is [", imageDirectory, "]",
                    ", imageTaskCron is [" + imageTaskCron + "]");
        }
    }
}
