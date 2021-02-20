package com.bla.imagefetch.upper.service.VO;

import java.util.Map;

/**
 * CommonAlgRequestVO 通用算法请求
 *
 * @author blacksea3(jxt)
 * @date 2020/8/11 23:20
 */
public class CommonAlgRequestVO {

    /** 内容 */
    private Map<String, String> content;
    /** 额外信息 */
    private Map<String, String> extInfo;

    /** getters and setters */
    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    @Override
    public String toString() {
        return "CommonAlgRequestVO{" +
                "content=" + content +
                ", extInfo=" + extInfo +
                '}';
    }
}
