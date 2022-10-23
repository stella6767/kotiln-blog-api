package com.example.simpleblog.mvc.config.scheduler

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

@EnableScheduling
@Configuration
class SchedulerConfig(

) {

    private val log = KotlinLogging.logger {  }

    private val schedulerPoolSize = 10

    private val schedularNamePrefix = "kang"

    @Bean
    fun threadPoolTaskScheduler() : TaskScheduler{
        val taskScheduler = ThreadPoolTaskScheduler()
        taskScheduler.poolSize = schedulerPoolSize
        taskScheduler.setThreadNamePrefix(schedularNamePrefix)
        taskScheduler.initialize()

        return taskScheduler
    }




}