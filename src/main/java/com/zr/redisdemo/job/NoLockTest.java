package com.zr.redisdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author zhangr
 * 2020/4/2 13:16
 */
@Service
@Slf4j
public class NoLockTest {

//    @Scheduled(cron = "0/5 * * * * ?")
    public void printDate() {
        log.info("---------Date:" + LocalDateTime.now() + "---------");
    }

}
