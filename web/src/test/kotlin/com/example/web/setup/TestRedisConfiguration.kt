package com.example.web.setup

import org.springframework.boot.test.context.TestConfiguration
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