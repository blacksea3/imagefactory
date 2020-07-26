package com.bla.imagefetch.common.dal.imagefactory.auto.dataobject;

import java.util.Date;

/**
 * The table 任务配置表
 */
public class TaskConfigDO{

    /**
     * id ID.
     */
    private Integer id;
    /**
     * name 任务配置名.
     */
    private String name;
    /**
     * status 状态.
     */
    private String status;
    /**
     * extInfo 额外信息.
     */
    private String extInfo;
    /**
     * description 描述.
     */
    private String description;
    /**
     * serviceName 服务名.
     */
    private String serviceName;
    /**
     * gmtCreate 创建时间.
     */
    private Date gmtCreate;
    /**
     * gmtModified 修改时间.
     */
    private Date gmtModified;

    /**
     * Set id ID.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set name 任务配置名.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 任务配置名.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set status 状态.
     */
    public void setStatus(String status){
        this.status = status;
    }

    /**
     * Get status 状态.
     *
     * @return the string
     */
    public String getStatus(){
        return status;
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
     * Set description 描述.
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Get description 描述.
     *
     * @return the string
     */
    public String getDescription(){
        return description;
    }

    /**
     * Set serviceName 服务名.
     */
    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    /**
     * Get serviceName 服务名.
     *
     * @return the string
     */
    public String getServiceName(){
        return serviceName;
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
