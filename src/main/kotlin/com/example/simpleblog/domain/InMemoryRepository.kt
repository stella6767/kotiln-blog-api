package com.example.simpleblog.domain

import java.util.*
import kotlin.collections.ArrayList

interface InMemoryRepository {

    fun clear()
    fun remove(key: String): Any?
    fun findAll(): MutableList<Any>
    fun findByKey(key: String): Any
    fun save(key: String, value: Any)
}