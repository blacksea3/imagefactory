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
import com.bla.imagefetch.common.util.FileUtil;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.upper.service.VO.CommonAlgRequestVO;
import com.bla.imagefetch.upper.service.VO.CommonAlgResponseVO;
import com.bla.imagefetch.upper.service.topAlg.localSecret.CusConstants;
import com.google.gson.Gson;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片风格处理 实现类
 *
 * 调阿里云API
 * 接口层
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4
 */
@Component("imitatePhotoStyle")
public class ImitatePhotoStyle extends CommonImageAlgBase implements CommonImageAlgInterface, InitializingBean {
    /** aLiYun client */
    static IAcsClient client = null;

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ImitatePhotoStyle.class);

    /**
     * 执行, 对外接口实现
     *
     * @author blacksea3(jxt)
     * @date 2020/8/11
     * @param commonAlgRequestVO: 请求
     * @return com.bla.imagefetch.upper.service.VO.CommonAlgResponseVO 结果
     */
    @Override
    public CommonAlgResponseVO exec(CommonAlgRequestVO commonAlgRequestVO) {
        //预处理: 生成结果变量
        CommonAlgResponseVO commonAlgResponseVO = new CommonAlgResponseVO();
        Map<String, String> content = commonAlgRequestVO.getContent();
        Map<String, String> result = new HashMap<>();

        //提取输入参数内容与格式校验
        if (content == null){
            commonAlgResponseVO.setSuccess(false);
            result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.INPUT_FIELD_MISS.getInfo());
            result.put(RESPONSE_DETAIL_KEY, null);
            commonAlgResponseVO.setResult(result);
            return commonAlgResponseVO;
        }

        String source = content.get("source");
        String ref = content.get("ref");
        String res = content.get("res");

        if (source == null || ref == null || res == null){
            commonAlgResponseVO.setSuccess(false);
            result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.INPUT_FIELD_MISS.getInfo());
            result.put(RESPONSE_DETAIL_KEY, null);
            commonAlgResponseVO.setResult(result);
            return commonAlgResponseVO;
        }

        //执行算法
        Pair<Boolean, String> ret = changeImageStyle(source, ref);

        //结果分析与图片下载
        if (ret.getKey()){
            Pair<Boolean, String> pRet = FileUtil.downloadFromRemoteUrl(ret.getValue(),
                    res.substring(0, res.lastIndexOf("\\")), res.substring(res.lastIndexOf("\\") + 1));
            if (!pRet.getKey()){
                commonAlgResponseVO.setSuccess(false);
                result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.DOWNLOAD_FILE_ERROR.getInfo());
                result.put(RESPONSE_DETAIL_KEY, pRet.getValue());
            }else{
                commonAlgResponseVO.setSuccess(true);
                result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.SUCCESS.getInfo());
                result.put(RESPONSE_DETAIL_KEY, "");
            }
        }else {
            commonAlgResponseVO.setSuccess(false);
            result.put(RESPONSE_DETAIL_KEY, ret.getValue());
            result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.INTERNAL_ALG_ERROR.getInfo());
        }
        commonAlgResponseVO.setResult(result);
        return commonAlgResponseVO;
    }

    /**
     * 修改图片风格
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param sourceImageFileName: 源文件路径
     * @param refImageFileName: 参考文件路径
     * @return javafx.util.Pair<java.lang.Boolean,java.lang.String> key:结果正确? value:信息
     */
    private Pair<Boolean, String> changeImageStyle(String sourceImageFileName, String refImageFileName){
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",             //默认
                CusConstants.ACCESS_KEYID,         //您的Access Key ID,
                CusConstants.SECRET);    //您的Access Key Secret,

        client = new DefaultAcsClient(profile);

        ImitatePhotoStyleRequest request = new ImitatePhotoStyleRequest();
        request.setRegionId("cn-shanghai");
        Pair<Boolean, String> res1 = genFileUrlFromLocal(sourceImageFileName);
        if (!res1.getKey()){
            return new Pair<>(false, res1.getValue());
        }
        request.setImageURL(res1.getValue());

        Pair<Boolean, String> res2 = genFileUrlFromLocal(refImageFileName);
        if (!res2.getKey()){
            return new Pair<>(false, res2.getValue());
        }
        request.setStyleUrl(res2.getValue());

        String ret = null;

        try {
            ImitatePhotoStyleResponse response = client.getAcsResponse(request);
            LoggerUtil.info(LOGGER, "图片处理结果", new Gson().toJson(response));
            ret = response.getData().getImageURL();
        } catch (ServerException e) {
            LoggerUtil.error(LOGGER, e,"图片处理失败");
            return new Pair<>(false, "图片处理失败" + e.getErrMsg());
        } catch (ClientException e) {
            String temp = "图片处理失败, ErrCode:" + e.getErrCode() + ", ErrMsg:" + e.getErrMsg() + ", RequestId:" + e.getRequestId();
            LoggerUtil.error(LOGGER, temp);
            return new Pair<>(false, temp);
        }
        return new Pair<>(true, ret);
    }

    @Override
    public void afterPropertiesSet(){
        LoggerUtil.info(LOGGER, "Bean of ImitatePhotoStyle has been initialized!");
    }
}
