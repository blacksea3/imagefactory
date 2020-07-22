package com.bla.imagefetch.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class mainScheduler {

    @Scheduled(cron = "0/5 * * * * *")
    void test(){
        System.out.println("test");
    }
}
