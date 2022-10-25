package com.example.simpleblog.mvc.config.redis.repo

import com.example.simpleblog.mvc.config.security.JwtManager
import mu.KotlinLogging
import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration
import java.util.*


open class RedisServiceImpl(
    private val redisTemplate: RedisTemplate<String,Any>
) : InMemoryService{

    private val log = KotlinLogging.logger {  }


    init {


    }

    override fun clear() {
        val keys = redisTemplate.keys("*")
        for (key in keys) {
            redisTemplate.delete(key)
        }
    }

    override fun remove(key: String): Any? {
        return redisTemplate.delete(key)
    }


    override fun findAll(): MutableList<Any> {
        val keys = redisTemplate.keys("*")
        val list = mutableListOf<Any>()
        for (key in keys) {
            val map = mutableMapOf<String, Any>()
            val value = findByKey(key)
            //log.info { "key=>$key  value==>$value" }
            map[key] = value
            list.add(map)
        }


        return list
    }

    override fun findByKey(key: String): Any {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElseThrow()
    }


    //@Transactional
    override fun save(key: String, value: Any) {

        redisTemplate.opsForValue().set(key,value, Duration.ofDays(7))
    }
}