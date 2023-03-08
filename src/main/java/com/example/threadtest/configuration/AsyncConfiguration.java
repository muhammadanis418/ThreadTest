package com.example.threadtest.configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {
    private static final Logger log = LogManager.getLogger(AsyncConfiguration.class);

  //  @Bean("CustomThread")

    @Override
    public Executor getAsyncExecutor() {
       // ExecutorService threadPoolTaskExecutor= Executors.newFixedThreadPool(5);
       ThreadPoolTaskExecutor threadPoolTaskExecutor =new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);  // minimum 5 thread are running
        threadPoolTaskExecutor.setMaxPoolSize(8);   // maximum  number of threads are 8
        threadPoolTaskExecutor.setQueueCapacity(100);  // If all threads are occupied then the task is saved in queue
        // and wait for queue to free and task is performed in that queue

        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true); // prevent interrupting running tasks
        // The pool size of 2 allows only two simultaneous tasks to execute so the third one is queued up.
        threadPoolTaskExecutor.setThreadNamePrefix("ThreadCustom-"); // Thread Name
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        log.warn("ThreadTestApplication.getAsyncUncaughtExceptionHandler");
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
