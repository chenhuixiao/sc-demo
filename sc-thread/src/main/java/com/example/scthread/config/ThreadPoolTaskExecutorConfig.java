package com.example.scthread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorConfig {
	//如果运行的线程大于或等于corePoolSize，那么就把task加入BlockQueue
	//如果当前运行的线程数小于corePoolSize，那么就创建线程来执行任务（执行时需要获取全局锁）
	private static final int corePoolSize = 2;
	//如果创建线程导致当前运行的线程数超过maximumPoolSize，就根据饱和策略来拒绝该任务
	private static final int maxPoolSize = 10;
	private static final int keepAliveTime = 10;
	//如果创建的线程数量大于BlockQueue的最大容量，那么创建新线程来执行该任务
	private static final int queueCapacity = 4;
	private static final String threadNamePrefix = "sc-thread-async-";
	
	@Bean("taskExecutor")
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);   
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveTime);
		executor.setThreadNamePrefix(threadNamePrefix);
		// 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}
}