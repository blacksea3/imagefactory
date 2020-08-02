package com.bla.imagefetch.common.dal.imagefactory.auto.dataobject;

import java.util.Date;

/**
 * The table 原子任务表
 */
public class TaskDetailDO{

    /**
     * id ID主键.
     */
    private Integer id;
    /**
     * script 脚本.
     */
    private String script;
    /**
     * status 状态.
     */
    private String status;
    /**
     * content 内容.
     */
    private String content;
    /**
     * extInfo 额外信息.
     */
    private String extInfo;
    /**
     * serviceName 服务名.
     */
    private String serviceName;
    /**
     * instanceName 任务实例名.
     */
    private String instanceName;
    /**
     * gmtCreate 创建时间.
     */
    private Date gmtCreate;
    /**
     * gmtModified 修改时间.
     */
    private Date gmtModified;

    /**
     * Set id ID主键.
     */
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * Get id ID主键.
     *
     * @return the string
     */
    public Integer getId(){
        return id;
    }

    /**
     * Set script 脚本.
     */
    public void setScript(String script){
        this.script = script;
    }

    /**
     * Get script 脚本.
     *
     * @return the string
     */
    public String getScript(){
        return script;
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
     * Set content 内容.
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * Get content 内容.
     *
     * @return the string
     */
    public String getContent(){
        return content;
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
     * Set instanceName 任务实例名.
     */
    public void setInstanceName(String instanceName){
        this.instanceName = instanceName;
    }

    /**
     * Get instanceName 任务实例名.
     *
     * @return the string
     */
    public String getInstanceName(){
        return instanceName;
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
