package com.bla.imagefetch.controller.DTO;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskDetailDO;

import java.io.Serializable;
import java.util.Date;

/**
 * TaskDetailDTO 原子任务DTO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/9 9:56
 */
public class TaskDetailDTO implements Serializable {

    private static final long serialVersionUID = 7169791884049913576L;

    /** id */
    private Integer id;

    /** 实例名 */
    private String instanceName;

    /** 状态 */
    private String status;

    /** 内容 */
    private String content;

    /** 服务名 */
    private String serviceName;

    /** 结果 */
    private String result;

    /** 创建时间 */
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

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
        return "TaskDetailDTO{" +
                "id=" + id +
                ", instanceName='" + instanceName + '\'' +
                ", status='" + status + '\'' +
                ", content='" + content + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", result='" + result + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public static TaskDetailDTO DOConvertToDTO(TaskDetailDO raw){
        TaskDetailDTO ret = new TaskDetailDTO();
        ret.setContent(raw.getContent());
        ret.setGmtCreate(raw.getGmtCreate());
        ret.setGmtModified(raw.getGmtModified());
        ret.setId(raw.getId());
        ret.setInstanceName(raw.getInstanceName());
        ret.setResult(raw.getResult());
        ret.setServiceName(raw.getServiceName());
        ret.setStatus(raw.getStatus());
        return ret;
    }
}
