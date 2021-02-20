package com.bla.imagefetch.upper.service.topAlg.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imageenhan.model.v20190930.ImitatePhotoStyleResponse;
import com.aliyuncs.imageenhan.model.v20190930.RecolorImageRequest;
import com.aliyuncs.imageenhan.model.v20190930.RecolorImageResponse;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RecoverImage 色彩迁移
 * 阿里云:https://vision.aliyun.com/experience/detail?spm=a211p3.14471187.J_7524944390.55.4eb4797dAaGzjU&tagName=imageenhan&children=RecolorImage
 *
 * @author blacksea3(jxt)
 * @date 2020/8/13 23:31
 */
@Component("recoverImage")
public class RecoverImage extends CommonImageAlgBase implements CommonImageAlgInterface, InitializingBean {
    /** aLiYun client */
    static IAcsClient client = null;

    private final static Logger LOGGER = LoggerFactory.getLogger(RecoverImage.class);
    
    /**
     * Description: 色彩迁移，使用色彩模板
     * 
     * @author blacksea3(jxt)
     * @date 2020/8/13
     * @param imageName: 图片名
     * @param color: 颜色
     * @return javafx.util.Pair<java.lang.Boolean,java.lang.String> true,null 或者 false,错误原因
     */
    private Pair<Boolean, String> recoverImage(String imageName, String color){
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",             //默认
                CusConstants.ACCESS_KEYID,         //您的Access Key ID,
                CusConstants.SECRET);    //您的Access Key Secret,

        client = new DefaultAcsClient(profile);

        RecolorImageRequest request = new RecolorImageRequest();
        request.setRegionId("cn-shanghai");
        Pair<Boolean, String> res1 = genFileUrlFromLocal(imageName);
        if (!res1.getKey()){
            return new Pair<>(false, res1.getValue());
        }
        request.setUrl(res1.getValue());
        request.setMode("TEMPLATE");

        List<RecolorImageRequest.ColorTemplate> colorTemplateList = new ArrayList<>();

        RecolorImageRequest.ColorTemplate colorTemplate1 = new RecolorImageRequest.ColorTemplate();
        colorTemplate1.setColor(color);
        colorTemplateList.add(colorTemplate1);
        request.setColorTemplates(colorTemplateList);

        String ret = null;
        try {
            RecolorImageResponse response = client.getAcsResponse(request);
            LoggerUtil.info(LOGGER, "图片处理结果", new Gson().toJson(response));
            List<String> rawRets = response.getData().getImageList();
            if (rawRets.size() != 1){
                LoggerUtil.error(LOGGER, "返回图片数量不为1, 返回信息为:", new Gson().toJson(response));
                return new Pair<>(false, "返回图片数量不为1, 返回信息为:" + new Gson().toJson(response));
            }
            ret = rawRets.get(0);
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

    /**
     * Description: 指定:对外接口
     *
     * @author blacksea3(jxt)
     * @date 2020/8/13
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
        String res = content.get("res");
        String color = content.get("color");

        if (source == null || res == null){
            commonAlgResponseVO.setSuccess(false);
            result.put(RESPONSE_INFO_KEY, RESPONSE_INFO_ENUM.INPUT_FIELD_MISS.getInfo());
            result.put(RESPONSE_DETAIL_KEY, null);
            commonAlgResponseVO.setResult(result);
            return commonAlgResponseVO;
        }

        //执行算法
        Pair<Boolean, String> ret = recoverImage(source, color);

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

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
