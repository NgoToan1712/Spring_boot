package com.company.opentalk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class SpringAsyncConfig {
    @Bean(name = "asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor tasKExecutor = new ThreadPoolTaskExecutor();
        tasKExecutor.setCorePoolSize(4); //tạo trong pool tối đa  thread
        tasKExecutor.setQueueCapacity(150);//số lương thread trong hàng đợi
        tasKExecutor.setMaxPoolSize(4);//số thread được tạo tooi đa
        tasKExecutor.setThreadNamePrefix("AsyncTaskThread-");
        tasKExecutor.initialize();
        return tasKExecutor;
    }
}
