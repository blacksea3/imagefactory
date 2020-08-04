package com.bla.imagefetch.upper.service.quartz;

import com.bla.imagefetch.common.util.LoggerUtil;
import com.bla.imagefetch.upper.service.task.AbstractTask;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * quartz 定时器的 主Job类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
@Component
public class MainJob implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainJob.class);

    @Autowired
    private AbstractTask abstractTask;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LoggerUtil.info(LOGGER, "MainJob triggered");

        abstractTask.executeTask();
        //for (String string:FileUtil.findAllPicFiles("../系统分析")){
        //    LoggerUtil.info(LOGGER, string);
        //}

        //LoggerUtil.info(LOGGER,
        //        "Hello world!:" + jobExecutionContext.getJobDetail().getKey());
    }
}
