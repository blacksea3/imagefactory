package com.bla.imagefetch.common.dal.imagefactory.auto.dataobject;

import java.util.Date;

/**
 * The table 服务配置表
 */
public class ServiceConfigDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * name 服务配置名.
     */
    private String name;
    /**
     * extInfo 额外信息.
     */
    private String extInfo;
    /**
     * sysName 系统名.
     */
    private String sysName;
    /**
     * beanName bean名.
     */
    private String beanName;
    /**
     * beanType bean类型.
     */
    private String beanType;
    /**
     * gmtCreate 创建时间.
     */
    private Date gmtCreate;
    /**
     * gmtModified 修改时间.
     */
    private Date gmtModified;

    /**
     * Set id 主键.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id 主键.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set name 服务配置名.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 服务配置名.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set extInfo 额外信息.
     */
    public void setExtInfo(String extInfo){
        this.extInfo = extInfo;
    }

    /**
     * Get extInfo 额外信息.
     *
     * @return the string
     */
    public String getExtInfo(){
        return extInfo;
    }

    /**
     * Set sysName 系统名.
     */
    public void setSysName(String sysName){
        this.sysName = sysName;
    }

    /**
     * Get sysName 系统名.
     *
     * @return the string
     */
    public String getSysName(){
        return sysName;
    }

    /**
     * Set beanName bean名.
     */
    public void setBeanName(String beanName){
        this.beanName = beanName;
    }

    /**
     * Get beanName bean名.
     *
     * @return the string
     */
    public String getBeanName(){
        return beanName;
    }

    /**
     * Set beanType bean类型.
     */
    public void setBeanType(String beanType){
        this.beanType = beanType;
    }

    /**
     * Get beanType bean类型.
     *
     * @return the string
     */
    public String getBeanType(){
        return beanType;
    }

    /**
     * Set gmtCreate 创建时间.
     */
    public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }

    /**
     * Get gmtCreate 创建时间.
     *
     * @return the string
     */
    public Date getGmtCreate(){
        return gmtCreate;
    }

    /**
     * Set gmtModified 修改时间.
     */
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }

    /**
     * Get gmtModified 修改时间.
     *
     * @return the string
     */
    public Date getGmtModified(){
        return gmtModified;
    }
}
