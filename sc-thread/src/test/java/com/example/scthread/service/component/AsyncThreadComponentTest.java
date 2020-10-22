package com.example.scthread.service.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AsyncThreadComponentTest {
    @Autowired
    private AsyncThreadComponent asyncThreadComponent;

    @Test
    void asyncDemo() {
        asyncThreadComponent.asyncDemo1("");
        asyncThreadComponent.asyncDemo2("");

        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        int noThreads = currentGroup.activeCount();

        Thread[] lstThreads = new Thread[noThreads];

        currentGroup.enumerate(lstThreads);

        for (int i = 0; i < noThreads; i++)

            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());

    }

}