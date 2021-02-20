package com.bla.imagefetch.controller.DTO;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;

import java.io.Serializable;
import java.util.Date;

/**
 * ServiceConfigDTO 服务配置 数据传输对象
 *
 * @author blacksea3(jxt)
 * @date 2020/8/5 23:17
 */
public class ServiceConfigDTO implements Serializable {

    private static final long serialVersionUID = -8349109210830190774L;

    /** id */
    private Integer id;
    /** 名字 */
    private String name;
    /** 系统名 */
    private String sysName;
    /** bean名 */
    private String beanName;
    /** bean类型 */
    private String beanType;
    /** 额外信息 */
    private String extInfo;
    /** 增加时间 */
    private Date gmtCreate;
    /** 修改时间 */
    private Date gmtModified;

    public static ServiceConfigDO DTOconvertToDO(ServiceConfigDTO raw){
        ServiceConfigDO ret = new ServiceConfigDO();
        ret.setId(raw.getId());
        ret.setBeanName(raw.getBeanName());
        ret.setBeanType(raw.getBeanType());
        ret.setExtInfo(raw.getExtInfo());
        ret.setName(raw.getName());
        ret.setSysName(raw.getSysName());
        return ret;
    }

    public static ServiceConfigDTO DOconvertToDTO(ServiceConfigDO raw){
        ServiceConfigDTO ret = new ServiceConfigDTO();
        ret.setGmtCreate(raw.getGmtCreate());
        ret.setGmtModified(raw.getGmtModified());
        ret.setId(raw.getId());
        ret.setBeanName(raw.getBeanName());
        ret.setBeanType(raw.getBeanType());
        ret.setExtInfo(raw.getExtInfo());
        ret.setName(raw.getName());
        ret.setSysName(raw.getSysName());
        return ret;
    }

    /** getters and setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "ServiceConfigDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sysName='" + sysName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanType='" + beanType + '\'' +
                ", extInfo='" + extInfo + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
