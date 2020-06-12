package com.javanorth.spring.springbootlog4j2.controller;

import com.javanorth.spring.springbootlog4j2.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private Logger logger = LoggerFactory.getLogger(LogController.class);

    @GetMapping("/log")
    public void test() {

        LoggerUtil.info(this.getClass(), "This is info log");
        logger.info("this is log...");
        for (int i = 0; i < 100; i++) {
            LoggerUtil.info(LogController.class, "This is info log");
            LoggerUtil.warn(LogController.class,"This is warning log");
            LoggerUtil.error(LogController.class,"This is error log");
        }
    }
//
    @Scheduled(cron = "0/2 * * * * ?")
    public void test1() {
        for (int i = 0; i < 2; i++) {
            LoggerUtil.info(this.getClass(),"This is info log");
            LoggerUtil.warn(this.getClass(),"This is warning log");
            LoggerUtil.error(this.getClass(),"This is error log");
        }
    }

//    public void test2() {
//        LoggerUtil.info(this.getClass(), "this is --");
//    }

}
