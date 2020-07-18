package com.bla.demo;

import com.alibaba.fastjson.JSON;
import com.aliyun.com.viapi.FileUtils;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.imageenhan.model.v20190930.ExtendImageStyleRequest;
import com.aliyuncs.imageenhan.model.v20190930.ExtendImageStyleResponse;
import com.aliyuncs.imageenhan.model.v20190930.ImitatePhotoStyleRequest;
import com.aliyuncs.imageenhan.model.v20190930.ImitatePhotoStyleResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.bla.secret.CusConstants;
import com.google.gson.Gson;

import java.io.IOException;

public class ImageDemo {
    static IAcsClient client = null;



    /**
     * 生成url，阿里云oss的，从本地文件
     *
     * 参考：https://help.aliyun.com/document_detail/155645.html?spm=a2c1g.8271268.0.0.4eb0df25sPWSHU
     *
     * @param fileName 图片路径
     * @throws ClientException .
     * @throws IOException .
     */
    public static String genFileUrlFromLocal(String fileName) throws ClientException, IOException {
        FileUtils fileUtils = FileUtils.getInstance(CusConstants.ACCESS_KEYID, CusConstants.SECRET); //您的Access Key ID,您的Access Key Secret,
        String ossurl = fileUtils.upload(fileName);
        System.out.println("genurlis::::" + ossurl);
        return ossurl;
    }

    public static void main(String[] args) throws Exception {

        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",             //默认
                CusConstants.ACCESS_KEYID,         //您的Access Key ID,
                CusConstants.SECRET);    //您的Access Key Secret,

        client = new DefaultAcsClient(profile);

        ImitatePhotoStyleRequest request = new ImitatePhotoStyleRequest();
        request.setRegionId("cn-shanghai");
        request.setImageURL(genFileUrlFromLocal("source.png"));
        request.setStyleUrl(genFileUrlFromLocal("ref.png"));

        try {
            ImitatePhotoStyleResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

        //testExtendImageStyle();
    }

    public static void testExtendImageStyle() throws Exception {
        ExtendImageStyleRequest req = new ExtendImageStyleRequest();
        System.out.println("--------  图像风格迁移 --------------");
        req.setStyleUrl(
                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ExtendImageStyle/majorUrl.jpeg");
        req.setMajorUrl(
                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ExtendImageStyle/styleUrl.jpeg");
        ExtendImageStyleResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
    }

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

    public static void printResponse(String actionName, String requestId, AcsResponse  data) {
        System.out.println(String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data) ));
    }
}
