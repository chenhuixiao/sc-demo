package com.example.scthread.controller;

import com.alibaba.fastjson.JSON;
import com.example.scthread.dao.PersonJpa;
import com.example.scthread.service.component.AsyncThreadComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@Api(tags = "异步线程demo")
@RequestMapping(value = "async-thread")
public class AsyncThreadController {
    @Resource
    private PersonJpa personJpa;
    @Autowired
    private AsyncThreadComponent asyncThreadComponent;

    @ApiOperation(value = "测试异步线程的生成与执行")
    @GetMapping("create")
    public String createAsyncThread(){
        ConcurrentHashMap<String, Set<String>> thread4TaskIdMap = AsyncThreadComponent.thread4TaskIdMap;
        String taskId = UUID.randomUUID().toString().replace("-", "");
        asyncThreadComponent.asyncDemo1(taskId);
        asyncThreadComponent.asyncDemo2(taskId);
        return taskId;
    }

    @ApiOperation(value = "测试异步线程的中断和后续处理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务id", example = "uuid12345555", dataType = "String", required = true)
    })
    @GetMapping("interrupt")
    public boolean interruptAsyncThread(String taskId){
        //todo 设置任务id对应的任务状态为中断或者取消（用户取消执行中的任务）

        //todo 根据任务id获取对应异步线程

        //todo 获取当前线程组中active状态的线程，并遍历中断任务对应的异步线程

        //todo 在异步线程的业务逻辑中，对中断和自然结束作相应后续处理

        ConcurrentHashMap<String, Set<String>> thread4TaskIdMap = AsyncThreadComponent.thread4TaskIdMap;
        Set<String> set = thread4TaskIdMap.get(taskId);
        log.info("任务id = {}, 执行的线程 = {}", taskId, JSON.toJSONString(set));

        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int activeCount = currentGroup.activeCount();
        Thread[] activeThreads = new Thread[activeCount];
        currentGroup.enumerate(activeThreads);
        log.info("当前线程组 = {}, 激活状态的线程 = {}", currentGroup.getName(), JSON.toJSONString(activeThreads));

        for (Thread thread : activeThreads) {
            for (String threadName : set) {
                if (thread.getName().equals(threadName)) {
                    log.info("中断线程{}", threadName);
                    thread.interrupt();
                }
            }

//            set.parallelStream().map(threadName -> {
//                if (thread.getName().equals(threadName)) {
//                    log.info("中断线程{}", threadName);
//                    thread.interrupt();
//                    return true;
//                }
//                return false;
//            });

        }
        return true;
    }
}