package com.bla.imagefetch.upper.service.VO;

/**
 * TaskInstanceAndDetailVO 任务实例与原子任务 VO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 21:13
 */
public class TaskInstanceAndDetailVO {

    /** 文件夹路径 */
    private String directory;

    /** 任务配置名 */
    private String configName;

    /** 服务名 */
    private String serviceName;

    /** getters and setters */
    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "TaskInstanceAndDetailVO{" +
                "directory='" + directory + '\'' +
                ", configName='" + configName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
