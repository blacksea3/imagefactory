package com.bla.imagefetch.upper.service.quartz;

import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.upper.service.quartz.config.MyJobFactory;
import com.bla.imagefetch.upper.service.quartz.config.QuartzConfiguration;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 主定时任务(触发器)
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
@Component
public class MainJobDetailBean implements InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainJobDetailBean.class);

    /**
     * bean请查看
     * @see QuartzConfiguration#scheduler()
     */
    @Resource(name = "mainScheduler")
    private Scheduler scheduler;

    /**
     * 当bean被注入后，启动定时器
     * @author jiaxiantao(blacksea3)
     * @date 2020/7/23
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of MainJobDetailBean has been initialized!");
    }

    /**
     * Description: 启动定时任务
     *
     * @author blacksea3(jxt)
     * @date 2020/8/3
     */
    public void startScheduler() throws SchedulerException {
        // 启动scheduler
        scheduler.start();
        // 创建HelloworldJob的JobDetail实例，并设置name/group
        JobDetail jobDetail = JobBuilder.newJob(MainJob.class)
                .withIdentity("myJob","myJobGroup1")
                //JobDataMap可以给任务传递参数
                .usingJobData("job_param","job_param1")
                .build();
        // 创建Trigger触发器设置使用cronSchedule方式调度
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","myTriggerGroup1")
                .usingJobData("job_trigger_param","job_trigger_param1")
                .startNow()
                //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ? *"))
                .build();
        // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
