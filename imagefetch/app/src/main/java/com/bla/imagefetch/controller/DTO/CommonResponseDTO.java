package com.bla.imagefetch.controller.DTO;

import java.io.Serializable;

/**
 * CommonResponseDTO 通用响应 数据传输模型
 *
 * @author blacksea3(jxt)
 * @date 2020/8/5 23:22
 */
public class CommonResponseDTO implements Serializable {

    private static final long serialVersionUID = 8584877020237126447L;
    /** 是否成功 */
    private boolean success;
    /** 信息 */
    private String info;

    /** getters and setters */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CommonResponseDTO{" +
                "success=" + success +
                ", info='" + info + '\'' +
                '}';
    }
}
