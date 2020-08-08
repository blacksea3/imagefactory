package com.bla.imagefetch.controller.DTO;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskInstanceDO;

import java.io.Serializable;
import java.util.Date;

/**
 * TaskInstanceDTO 任务实例 DTO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/8 9:28
 */
public class TaskInstanceDTO implements Serializable {

    private static final long serialVersionUID = 8154866968918785082L;

    /** id */
    private Integer id;
    /** 实例名 */
    private String name;
    /** 描述 */
    private String description;
    /** 任务配置名 */
    private String configName;
    /** 状态 */
    private String status;
    /** 优先级 */
    private Integer priority;
    /** 总原子任务数 */
    private Integer totalNum;
    /** 已处理原子任务数 */
    private Integer handleNum;
    /** 服务名 */
    private String serviceName;
    /** 添加时间 */
    private Date gmtCreate;
    /** 修改时间 */
    private Date gmtModified;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getHandleNum() {
        return handleNum;
    }

    public void setHandleNum(Integer handleNum) {
        this.handleNum = handleNum;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
        return "TaskInstanceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", configName='" + configName + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", totalNum=" + totalNum +
                ", handleNum=" + handleNum +
                ", serviceName='" + serviceName + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static TaskInstanceDO DTOconvertToDO(TaskInstanceDTO raw){
        TaskInstanceDO ret = new TaskInstanceDO();
        ret.setStatus(raw.getStatus());
        ret.setName(raw.getName());
        ret.setConfigName(raw.getConfigName());
        ret.setServiceName(raw.getServiceName());
        ret.setDescription(raw.getDescription());
        ret.setPriority(raw.getPriority());
        ret.setHandleNum(raw.getHandleNum());
        ret.setTotalNum(raw.getTotalNum());
        return ret;
    }

    public static TaskInstanceDTO DOconvertToDTO(TaskInstanceDO raw){
        TaskInstanceDTO ret = new TaskInstanceDTO();
        ret.setId(raw.getId());
        ret.setStatus(raw.getStatus());
        ret.setName(raw.getName());
        ret.setConfigName(raw.getConfigName());
        ret.setServiceName(raw.getServiceName());
        ret.setGmtCreate(raw.getGmtCreate());
        ret.setGmtModified(raw.getGmtModified());
        ret.setDescription(raw.getDescription());
        ret.setPriority(raw.getPriority());
        ret.setHandleNum(raw.getHandleNum());
        ret.setTotalNum(raw.getTotalNum());
        return ret;
    }

}


