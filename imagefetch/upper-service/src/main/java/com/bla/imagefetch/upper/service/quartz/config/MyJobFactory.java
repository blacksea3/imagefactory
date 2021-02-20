package com.bla.imagefetch.upper.service.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义 job 工厂
 * 参考 https://www.cnblogs.com/liwangwang/p/12627433.html
 *
 * @author blacksea3(jxt)
 * @date 2020/8/3 23:27
 */
@Component
public class MyJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     *
     *
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 实例化对象
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入（Spring管理该Bean）
        capableBeanFactory.autowireBean(jobInstance);
        //返回对象
        return jobInstance;
    }
}
