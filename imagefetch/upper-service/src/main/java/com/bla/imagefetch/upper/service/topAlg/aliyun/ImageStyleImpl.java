package com.bla.imagefetch.upper.service.topAlg.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.com.viapi.FileUtils;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imageenhan.model.v20190930.ImitatePhotoStyleRequest;
import com.aliyuncs.imageenhan.model.v20190930.ImitatePhotoStyleResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.upper.service.topAlg.localSecret.CusConstants;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 图片风格处理 实现类
 *
 * 调阿里云API
 * 接口层
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4
 */
@Component
public class ImageStyleImpl implements ImageStyle, InitializingBean {
    /** aLiYun client */
    static IAcsClient client = null;

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageStyleImpl.class);

    /**
     * 生成url，阿里云oss的，从本地文件
     *
     * 参考：https://help.aliyun.com/document_detail/155645.html?spm=a2c1g.8271268.0.0.4eb0df25sPWSHU
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param fileName 图片路径
     * @return java.lang.String 生成图片url, 尽快下载, 自动下载
     */

    private String genFileUrlFromLocal(String fileName){
        FileUtils fileUtils = null; //您的Access Key ID,您的Access Key Secret,
        try {
            fileUtils = FileUtils.getInstance(CusConstants.ACCESS_KEYID, CusConstants.SECRET);
        } catch (ClientException e) {
            LoggerUtil.error(LOGGER, e, "阿里云账号登陆失败，ACCESS_KEYID/SECRET不存在或不匹配，请检查\n",
                    "ACCESS_KEYID:", CusConstants.ACCESS_KEYID,
                    "\nSECRET:", CusConstants.SECRET);
        }

        String ossurl = null;
        try {
            ossurl = fileUtils.upload(fileName);
        } catch (ClientException e) {
            LoggerUtil.error(LOGGER, e, "文件上传失败");
            //e.printStackTrace();
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e, "文件找不到");
            //e.printStackTrace();
        }
        LoggerUtil.info(LOGGER, "成功生成url:", ossurl);
        return ossurl;
    }

    /**
     * 修改图片风格
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param sourceImageFileName: 源文件路径
     * @param refImageFileName: 参考文件路径
     * @return java.lang.String 生成图片url, 尽快下载, 自动下载
     */
    @Override
    public String changeImageStyle(String sourceImageFileName, String refImageFileName) throws IOException, ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",             //默认
                CusConstants.ACCESS_KEYID,         //您的Access Key ID,
                CusConstants.SECRET);    //您的Access Key Secret,

        client = new DefaultAcsClient(profile);

        ImitatePhotoStyleRequest request = new ImitatePhotoStyleRequest();
        request.setRegionId("cn-shanghai");
        request.setImageURL(genFileUrlFromLocal(sourceImageFileName));
        request.setStyleUrl(genFileUrlFromLocal(refImageFileName));

        String ret = null;

        try {
            ImitatePhotoStyleResponse response = client.getAcsResponse(request);
            LoggerUtil.info(LOGGER, "图片处理结果", new Gson().toJson(response));
            ret = response.getData().getImageURL();
        } catch (ServerException e) {
            LoggerUtil.error(LOGGER, e,"图片处理失败");
            //e.printStackTrace();
        } catch (ClientException e) {
            LoggerUtil.error(LOGGER, "图片处理失败, ErrCode:", e.getErrCode(),
                    "\tErrMsg:", e.getErrMsg(),
                    "\te.getErrMsg():", e.getRequestId());
        }
        return ret;
    }


    /**
     * 获取结果
     * 参考：https://help.aliyun.com/document_detail/155645.html?spm=a2c1g.8271268.0.0.4eb0df25sPWSHU
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param req:
     * @return T
     */
    private static <R extends RpcAcsRequest<T>, T extends AcsResponse> T getAcsResponse(R req) throws Exception {
        try {
            return client.getAcsResponse(req);
        } catch (ServerException e) {
            // 服务端异常
            System.out.println(String.format("ServerException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (ClientException e) {
            // 客户端错误
            System.out.println(String.format("ClientException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            throw e;
        }
    }

    /**
     * 提取返回值
     * 参考：https://help.aliyun.com/document_detail/155645.html?spm=a2c1g.8271268.0.0.4eb0df25sPWSHU
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param actionName: .
     * @param requestId: .
     * @param data: .
     * @return java.lang.String
     */
    private String extractResponse(String actionName, String requestId, AcsResponse data) {
        LoggerUtil.info(LOGGER, String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data)));
        return JSON.toJSONString(data);
    }

    @Override
    public void afterPropertiesSet(){
        LoggerUtil.info(LOGGER, "Bean of ImageStyleImpl has been initialized!");
    }
}
