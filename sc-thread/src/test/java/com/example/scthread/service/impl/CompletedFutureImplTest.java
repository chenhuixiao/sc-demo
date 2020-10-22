package com.example.scthread.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CompletedFutureImplTest {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    void secondSleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            log.info("当前线程 = {}", Thread.currentThread().getName());
            secondSleep(10);
            return s.toUpperCase();
        }, taskExecutor);

        if ( !cf.isDone() )  {
            log.info("等待...");
            secondSleep(15);
        }

        log.info("结果 = {}", cf.getNow(null));
    }
}