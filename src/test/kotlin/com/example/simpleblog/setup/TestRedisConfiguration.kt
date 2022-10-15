package com.example.simpleblog.setup

import com.example.simpleblog.domain.InMemoryRepository
import com.example.simpleblog.domain.RedisRepositoryImpl
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@TestConfiguration
class TestRedisConfiguration {

    private var redisServer: RedisServer = RedisServer(6379)

    @PostConstruct
    fun postConstruct() {
        redisServer.start()
    }

    @PreDestroy
    fun preDestroy() {
        redisServer.stop()
    }





}