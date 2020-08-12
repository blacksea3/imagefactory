package com.bla.imagefetch.upper.service.topAlg.aliyun;

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

    /** 响应 简要信息 枚举 */
    protected enum RESPONSE_INFO_ENUM{

        INPUT_FIELD_MISS("输入缺少必要参数"),
        INPUT_FIELD_INVALID("输入必要参数格式错误"),
        INTERNAL_ALG_ERROR("内部算法错误"),
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

}
