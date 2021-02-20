package com.bla.imagefetch.upper.service.VO;

/**
 * TaskConfigVO 任务配置 VO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 21:08
 */
public class TaskConfigVO {

    /** 名字 */
    private String name;

    /** 描述 */
    private String description;

    /** 状态 */
    private String status;

    /** 任务配置VO 状态枚举类 */
    public enum ENUM_TASK_CONFIG_VO_STATUS{
        VALID("valid");

        private final String _val;

        ENUM_TASK_CONFIG_VO_STATUS(String val){
            this._val = val;
        }

        public String get_val() {
            return _val;
        }
    }

    /** 服务名 */
    private String serviceName;

    /** getters and setters */
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "TaskConfigVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
