package com.bla.imagefetch.upper.service.quartz;

import com.bla.imagefetch.common.util.LoggerUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 主定时任务(触发器)
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
@Component
public class MainJobDetailBean implements InitializingBean {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainJobDetailBean.class);

    /**
     * 当bean被注入后，启动定时器
     * @author jiaxiantao(blacksea3)
     * @date 2020/7/23
     * @return void
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LoggerUtil.info(LOGGER, "Bean of MainJobDetailBean has been initialized!");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
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
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ? *"))
                .build();
        // 注册JobDetail实例到scheduler以及使用对应的Trigger触发时机
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
