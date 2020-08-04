package com.bla.imagefetch.upper.service.topAlg.aliyun;

import com.aliyuncs.exceptions.ClientException;

import java.io.IOException;

/**
 * 图片风格处理 接口
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4 15:46
 */
public interface ImageStyle {
    public String changeImageStyle(String sourceImageFileName, String refImageFileName) throws IOException, ClientException;
}
