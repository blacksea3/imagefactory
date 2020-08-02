package com.bla.imagefetch.upper.service.VO;

import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.ServiceConfigDO;
import com.bla.imagefetch.common.dal.imagefactory.auto.dataobject.TaskConfigDO;

/**
 * VOConvertToDO VO转换成DO类
 *
 * @author blacksea3(jxt)
 * @date 2020/8/2 21:32
 */
public class VOConvertToDO {

    public static TaskConfigDO VOTaskConfigDO(TaskConfigVO taskConfigVO){
        TaskConfigDO taskConfigDO = new TaskConfigDO();
        taskConfigDO.setStatus(taskConfigVO.getStatus());
        taskConfigDO.setServiceName(taskConfigVO.getServiceName());
        taskConfigDO.setDescription(taskConfigVO.getDescription());
        taskConfigDO.setName(taskConfigVO.getName());
        return taskConfigDO;
    }

    public static ServiceConfigDO VOServiceConfigDO(ServiceConfigVO serviceConfigVO){
        ServiceConfigDO serviceConfigDO = new ServiceConfigDO();
        serviceConfigDO.setSysName(serviceConfigVO.getSysName());
        serviceConfigDO.setName(serviceConfigVO.getName());
        serviceConfigDO.setExtInfo("");
        serviceConfigDO.setBeanType(serviceConfigVO.getBeanType().get_val());
        serviceConfigDO.setBeanName(serviceConfigVO.getBeanName());
        return serviceConfigDO;
    }

}
