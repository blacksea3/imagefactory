package com.bla.imagefetch.controller.DTO;

import java.io.Serializable;

/**
 * ClassName CommonStringRequestDTO 通用请求DTO
 * Description <br/>
 *
 * @author blacksea3(jxt) <br/>
 * @date 2020/8/9 10:55<br/>
 */
public class CommonStringRequestDTO implements Serializable {

    private static final long serialVersionUID = -4210982475850495851L;

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CommonStringRequestDTO{" +
                "info='" + info + '\'' +
                '}';
    }
}
