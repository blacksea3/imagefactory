package com.bla.imagefetch.controller.DTO;

import java.io.Serializable;

/**
 * ClassName CommonIntegerRequestDTO 通用数字DTO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/9 11:13
 */
public class CommonIntegerRequestDTO implements Serializable {

    private static final long serialVersionUID = -7626969011122029149L;

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CommonIntegerRequestDTO{" +
                "num=" + num +
                '}';
    }
}
