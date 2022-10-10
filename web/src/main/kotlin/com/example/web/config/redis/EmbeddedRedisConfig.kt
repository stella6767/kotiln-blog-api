package com.example.web.config.redis

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
@Profile("local")
class EmbeddedRedisConfig(

) {
    private val log = KotlinLogging.logger {  }
    lateinit var redisServer: RedisServer

    @Value("\${spring.redis.port}")
    val port:Int = 6379

    @PostConstruct
    fun init(){
        log.debug { "embedded redis Start port: $port" }
        this.redisServer = RedisServer(this.port)
        redisServer.start()
    }


    @PreDestroy
    fun destory(){
        log.debug { "embedded redis Stop!! " }

        this.redisServer.stop()
    }

}