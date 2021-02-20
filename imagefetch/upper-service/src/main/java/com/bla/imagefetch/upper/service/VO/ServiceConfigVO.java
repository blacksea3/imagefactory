package com.bla.imagefetch.upper.service.VO;

/**
 * ServiceConfigVO 服务配置 VO
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 21:02
 */
public class ServiceConfigVO {

    /** 名字 */
    private String name;

    /** 系统名 */
    private String sysName;

    /** bean名 */
    private String beanName;

    /** bean类型 */
    private ENUM_SERVICE_CONFIG_VO_BEAN_TYPE beanType;

    /** 服务配置VO bean类型枚举类 */
    public enum ENUM_SERVICE_CONFIG_VO_BEAN_TYPE{
        LOCAL("local");

        private final String _val;

        ENUM_SERVICE_CONFIG_VO_BEAN_TYPE(String val){
            this._val = val;
        }

        public String get_val() {
            return _val;
        }
    }

    /** getters and setters */
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

    public ENUM_SERVICE_CONFIG_VO_BEAN_TYPE getBeanType() {
        return beanType;
    }

    public void setBeanType(ENUM_SERVICE_CONFIG_VO_BEAN_TYPE beanType) {
        this.beanType = beanType;
    }

    @Override
    public String toString() {
        return "ServiceConfigVO{" +
                "name='" + name + '\'' +
                ", sysName='" + sysName + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanType=" + beanType +
                '}';
    }
}
