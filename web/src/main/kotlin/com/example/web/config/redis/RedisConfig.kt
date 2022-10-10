package com.example.web.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig {

    @Value("\${spring.redis.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis.port}")
    private val redisPort = 0

    @Value("\${spring.redis.database}")
    private val redisDatabase = 0

    /**
     * Factory
     *
     * @return
     */
    @Bean
    fun jedisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisHost, redisPort)
    }

    /**
     * redis Template
     *
     * @return
     */
    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.setConnectionFactory(jedisConnectionFactory()!!)
        redisTemplate.keySerializer = StringRedisSerializer()
        //redisTemplate.valueSerializer = StringRedisSerializer()
        return redisTemplate
    }

}