package com.example.web.config.redis

import com.example.web.config.redis.InMemoryRepository
import mu.KotlinLogging
import net.jodah.expiringmap.ExpirationPolicy
import java.util.*
import kotlin.collections.ArrayList
import net.jodah.expiringmap.ExpiringMap
import java.util.concurrent.TimeUnit


open class HashMapRepositoryImpl(

) : InMemoryRepository {

    private val log = KotlinLogging.logger {  }
    //private val store = ConcurrentHashMap<String, Any>()
    private val store:MutableMap<String, Any> = ExpiringMap.builder()
        .expiration(1000, TimeUnit.MILLISECONDS)
        .expirationPolicy(ExpirationPolicy.CREATED)
        .expirationListener{ key:String, value:Any ->
            log.info { "key: $key  , value: $value expired" }
        }
        .maxSize(1000000000)
        .build()


    override fun save(key:String, value:Any){

        Thread.sleep(50)
        store[key] = value
    }

    override fun findByKey(key: String): Any {
        return Optional.ofNullable(store.get(key)).orElseThrow{
            throw RuntimeException("not found refreshToken")
        }
    }

    override fun findAll(): MutableList<Any> {
        return ArrayList<Any>(store.values)
    }

    override fun remove(key: String): Any? {
        return store.remove(key)
    }

    override fun clear(){
        store.clear()
    }






}