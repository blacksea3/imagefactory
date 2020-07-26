package com.bla.imagefetch.common.dal.imagefactory.auto.dataobject;

import java.util.Date;

/**
 * The table 任务实例表
 */
public class TaskInstanceDO{

    /**
     * id 主键.
     */
    private Integer id;
    /**
     * name 实例名.
     */
    private String name;
    /**
     * status 状态.
     */
    private String status;
    /**
     * configName 任务配置名.
     */
    private String configName;
    /**
     * description 描述.
     */
    private String description;
    /**
     * serviceName 服务名.
     */
    private String serviceName;
    /**
     * priority 优先级.
     */
    private Integer priority;
    /**
     * totalNum 总原子任务数.
     */
    private Integer totalNum;
    /**
     * handleNum 已触发处理的个数.
     */
    private Integer handleNum;
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
     * Set name 实例名.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 实例名.
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
     * Set configName 任务配置名.
     */
    public void setConfigName(String configName){
        this.configName = configName;
    }

    /**
     * Get configName 任务配置名.
     *
     * @return the string
     */
    public String getConfigName(){
        return configName;
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
     * Set priority 优先级.
     */
    public void setPriority(Integer priority){
        this.priority = priority;
    }

    /**
     * Get priority 优先级.
     *
     * @return the string
     */
    public Integer getPriority(){
        return priority;
    }

    /**
     * Set totalNum 总原子任务数.
     */
    public void setTotalNum(Integer totalNum){
        this.totalNum = totalNum;
    }

    /**
     * Get totalNum 总原子任务数.
     *
     * @return the string
     */
    public Integer getTotalNum(){
        return totalNum;
    }

    /**
     * Set handleNum 已触发处理的个数.
     */
    public void setHandleNum(Integer handleNum){
        this.handleNum = handleNum;
    }

    /**
     * Get handleNum 已触发处理的个数.
     *
     * @return the string
     */
    public Integer getHandleNum(){
        return handleNum;
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
