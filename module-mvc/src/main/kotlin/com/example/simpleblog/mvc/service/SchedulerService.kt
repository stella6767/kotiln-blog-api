package com.example.simpleblog.mvc.service

import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.mvc.config.scheduler.MyCronExpression
import com.example.simpleblog.mvc.event.PostDeleteAtUpdateEvent
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.support.CronExpression
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class SchedulerService(
    private val eventPublisher: ApplicationEventPublisher,
) {

    private val log = KotlinLogging.logger {  }

    @Transactional
    @Scheduled(cron = MyCronExpression.tenSecond)
    fun schedulerTest(){
        log.info { "event 발행" }
        eventPublisher.publishEvent(PostDeleteAtUpdateEvent(LocalDateTime.now()))
    }

}