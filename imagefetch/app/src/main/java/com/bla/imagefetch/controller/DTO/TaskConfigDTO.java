package com.bla.imagefetch.controller.DTO;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;

import java.io.Serializable;
import java.util.Date;

/**
 * TaskConfigDTO 任务配置 数据传输对象
 *
 * @author blacksea3(jxt)
 * @date 2020/8/6 21:02
 */
public class TaskConfigDTO implements Serializable {

    private static final long serialVersionUID = -6682600247334146334L;

    /** Id */
    private Integer Id;

    /** 名字 */
    private String name;

    /** 描述 */
    private String description;

    /** 状态 */
    private String status;

    /** 额外信息 */
    private String extInfo;

    /** 服务名 */
    private String serviceName;

    /** 创建时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    /** getters and setters */
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
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
        return "TaskConfigDTO{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", extInfo='" + extInfo + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static TaskConfigDO DTOconvertToDO(TaskConfigDTO raw){
        TaskConfigDO ret = new TaskConfigDO();
        ret.setId(raw.getId());
        ret.setName(raw.getName());
        ret.setDescription(raw.getDescription());
        ret.setServiceName(raw.getServiceName());
        ret.setStatus(raw.getStatus());
        ret.setExtInfo(raw.getExtInfo());

        return ret;
    }

    public static TaskConfigDTO DOconvertToDTO(TaskConfigDO raw){
        TaskConfigDTO ret = new TaskConfigDTO();
        ret.setGmtCreate(raw.getGmtCreate());
        ret.setGmtModified(raw.getGmtModified());
        ret.setId(raw.getId());
        ret.setName(raw.getName());
        ret.setDescription(raw.getDescription());
        ret.setServiceName(raw.getServiceName());
        ret.setStatus(raw.getStatus());
        ret.setExtInfo(raw.getExtInfo());
        return ret;
    }
}
