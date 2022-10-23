package com.example.simpleblog.mvc.config.cache

enum class CacheType(
    val cacheName: String,
    val expriedAfterWrite:Long,
    val mazimumSize:Long,
    val cacheKey:String,
) {

    POST_NAME(Constant.postName, 100, 10_000, "postNameKey");
    
    object Constant {
        const val postName = "postName"
    }


}