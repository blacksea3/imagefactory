package com.bla.imagefetch.upper.service.VO;

import java.util.Map;

/**
 * CommonAlgResponseVO 通用算法响应VO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/11 23:22
 */
public class CommonAlgResponseVO {

    /** 结果 */
    private Map<String, String> result;
    /** 是否成功 */
    private boolean success;

    /** getters and setters */
    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "CommonAlgResponseVO{" +
                "result=" + result +
                ", success=" + success +
                '}';
    }
}
