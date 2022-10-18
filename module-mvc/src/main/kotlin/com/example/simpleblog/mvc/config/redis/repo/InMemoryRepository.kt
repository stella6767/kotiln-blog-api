package com.example.simpleblog.mvc.config.redis.repo

interface InMemoryRepository {

    fun clear()
    fun remove(key: String): Any?
    fun findAll(): MutableList<Any>
    fun findByKey(key: String): Any
    fun save(key: String, value: Any)
}