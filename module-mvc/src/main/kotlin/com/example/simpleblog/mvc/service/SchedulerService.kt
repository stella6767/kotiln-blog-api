package com.example.simpleblog.mvc.service

import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.mvc.config.scheduler.MyCronExpression
import mu.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.scheduling.support.CronExpression
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class SchedulerService(
    private val postRepository: PostRepository
) {


    private val log = KotlinLogging.logger {  }


    @Transactional
    @Scheduled(cron = MyCronExpression.tenSecond)
    fun schedulerTest(){


        log.info { "??????????" }

        val allPosts = postRepository.findAll()
        val filterPosts: List<Post> = allPosts.filter {
            it.reservateAt != null && LocalDateTime.now().isAfter(it.reservateAt)
        }

        val ids = filterPosts.map {
            it.id
        }

        log.info { "update 공개 execute count=> ${postRepository.updateDeleteAtByReservateAt(ids)}" }
    }


}