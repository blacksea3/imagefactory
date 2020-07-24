package com.bla.imagefetch.quartz;

import com.bla.imagefetch.util.FileUtil;
import com.bla.imagefetch.util.LoggerUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * quartz 定时器的 主Job类
 * @author jiaxiantao(blacksea3)
 * @date 2020/7/23
 */
public class MainJob implements Job {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LoggerUtil.info(LOGGER, "triggered");
        for (String string:FileUtil.findAllPicFiles("../系统分析")){
            LoggerUtil.info(LOGGER, string);
        }

        //LoggerUtil.info(LOGGER,
        //        "Hello world!:" + jobExecutionContext.getJobDetail().getKey());
    }
}
