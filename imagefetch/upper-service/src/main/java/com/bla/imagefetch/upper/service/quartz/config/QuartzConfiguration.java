package com.bla.imagefetch.upper.service.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * QuartzConfiguration quartz 配置类
 *
 * 参考 https://www.cnblogs.com/liwangwang/p/12627433.html
 *
 * @author blacksea3(jxt)
 * @date 2020/8/4 15:03
 */
@Configuration
public class QuartzConfiguration {
    @Autowired
    private MyJobFactory myJobFactory;

    //创建调度器工厂
    @Bean(name = "mainSchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(myJobFactory);
        return factoryBean;
    }

    @Bean(name="mainScheduler")
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
