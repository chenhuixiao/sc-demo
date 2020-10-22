package com.example.scthread.service.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AsyncThreadComponent {
    public static ConcurrentHashMap<String, Set<String>> thread4TaskIdMap = new ConcurrentHashMap<>();
    private int sleepTime = 100000;

    @Async("taskExecutor")
    public void asyncDemo1(String taskId) {
        long time = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        Set<String> set = thread4TaskIdMap.get(taskId);
        if (set == null) {
            Set<String> set1 = new HashSet<>();
            set1.add(threadName);
            thread4TaskIdMap.put(taskId, set1);
        } else {
            set.add(threadName);
        }

        try {
            log.info("线程名 = {}, 线程执行中...", threadName);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.info("线程名 = {}, 线程中断", threadName);

        } finally {
            log.info("线程名 = {}, 线程执行完毕, 耗时 = {}ms", threadName, System.currentTimeMillis() - time);
        }
    }

    @Async("taskExecutor")
    public void asyncDemo2(String taskId) {
        long time = System.currentTimeMillis();
        String threadName = Thread.currentThread().getName();
        Set<String> set = thread4TaskIdMap.get(taskId);
        if (set == null) {
            Set<String> set1 = new HashSet<>();
            set1.add(threadName);
            thread4TaskIdMap.put(taskId, set1);
        } else {
            set.add(threadName);
        }

        try {
            log.info("线程名 = {}, 线程执行中...", threadName);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.info("线程名 = {}, 线程中断", threadName);

        } finally {
            log.info("线程名 = {}, 线程执行完毕, 耗时 = {}ms", threadName, System.currentTimeMillis() - time);
        }
    }
}
