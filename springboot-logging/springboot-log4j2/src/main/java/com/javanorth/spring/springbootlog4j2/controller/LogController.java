package com.javanorth.spring.springbootlog4j2.controller;

import com.javanorth.spring.springbootlog4j2.util.LogUtil;
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

        LogUtil.info(this.getClass(), "This is info log");
        logger.info("this is log...");
        for (int i = 0; i < 100; i++) {
            LogUtil.info(LogController.class, "This is info log");
            LogUtil.warn(LogController.class,"This is warning log");
            LogUtil.error(LogController.class,"This is error log");
        }
    }

    /**
     * 用于测试
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void test1() {
        for (int i = 0; i < 2; i++) {
            LogUtil.info(this.getClass(),"This is info log");
            LogUtil.warn(this.getClass(),"This is warning log");
            LogUtil.error(this.getClass(),"This is error log");
        }
    }


}
