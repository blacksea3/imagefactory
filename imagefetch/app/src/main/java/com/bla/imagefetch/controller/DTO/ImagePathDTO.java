package com.bla.imagefetch.controller.DTO;

import java.io.Serializable;

/**
 * ClassName ImagePathDTO 图片路径DTO
 * Description <br/>
 *
 * @author blacksea3(jxt) <br/>
 * @date 2020/8/8 21:43<br/>
 */
public class ImagePathDTO implements Serializable {

    private static final long serialVersionUID = -2900530348341369737L;

    /** 路径 */
    private String path;

    /** setters and getters */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ImagePathDTO{" +
                "path='" + path + '\'' +
                '}';
    }
}
