package com.bla.imagefetch.upper.service.topAlg.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.com.viapi.FileUtils;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.upper.service.topAlg.localSecret.CusConstants;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * CommonImageAlgBase 通用图片算法基类
 *
 * @author blacksea3(jxt) <br/>
 * @date 2020/8/11 23:28<br/>
 */
public class CommonImageAlgBase {

    /** 响应 简要信息 字段 */
    protected final String RESPONSE_INFO_KEY = "info";

    /** 响应 详细内容 字段 */
    protected final String RESPONSE_DETAIL_KEY = "detail";

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonImageAlgBase.class);

    /** 响应 简要信息 枚举 */
    protected enum RESPONSE_INFO_ENUM{

        INPUT_FIELD_MISS("输入缺少必要参数"),
        INPUT_FIELD_INVALID("输入必要参数格式错误"),
        INTERNAL_ALG_ERROR("内部算法错误"),
        DOWNLOAD_FILE_ERROR("下载文件错误"),
        OTHER_ERROR("其他错误"),
        SUCCESS("正确");

        private final String info;

        private RESPONSE_INFO_ENUM(String s){
            this.info = s;
        }

        public String getInfo() {
            return info;
        }
    };

    /**
     * 生成url，阿里云oss的，从本地文件
     *
     * 参考：https://help.aliyun.com/document_detail/155645.html?spm=a2c1g.8271268.0.0.4eb0df25sPWSHU
     *
     * @author blacksea3(jxt)
     * @date 2020/8/4
     * @param fileName 图片路径
     * @return javafx.util.Pair<java.lang.Boolean,java.lang.String> key:结果正确? value:信息
     */
    protected Pair<Boolean, String> genFileUrlFromLocal(String fileName){
        FileUtils fileUtils = null; //您的Access Key ID,您的Access Key Secret,
        try {
            fileUtils = FileUtils.getInstance(CusConstants.ACCESS_KEYID, CusConstants.SECRET);
        } catch (ClientException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("阿里云账号登陆失败，ACCESS_KEYID/SECRET不存在或不匹配，请检查, ");
            stringBuilder.append("ACCESS_KEYID:");
            stringBuilder.append(CusConstants.ACCESS_KEYID);
            stringBuilder.append(", SECRET:");
            stringBuilder.append(CusConstants.SECRET);
            String ret = stringBuilder.toString();
            LoggerUtil.error(LOGGER, e, ret);
            return new Pair<>(false, ret);
        }

        String ossurl = null;
        try {
            ossurl = fileUtils.upload(fileName);
        } catch (ClientException e) {
            LoggerUtil.error(LOGGER, e, "文件生成url失败", fileName);
            return new Pair<>(false, "文件生成url失败" + fileName);
            //e.printStackTrace();
        } catch (IOException e) {
            LoggerUtil.error(LOGGER, e, "文件找不到", fileName);
            return new Pair<>(false, "文件找不到" + fileName);
            //e.printStackTrace();
        }
        LoggerUtil.info(LOGGER, "成功生成url:", ossurl);
        return new Pair<>(true, ossurl);
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
    protected String extractResponse(String actionName, String requestId, AcsResponse data) {
        LoggerUtil.info(LOGGER, String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data)));
        return JSON.toJSONString(data);
    }

}
