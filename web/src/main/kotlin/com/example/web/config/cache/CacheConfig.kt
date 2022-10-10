package com.example.web.config.cache

import mu.KotlinLogging
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import org.ehcache.config.units.MemoryUnit
import org.ehcache.event.CacheEvent
import org.ehcache.event.CacheEventListener
import org.ehcache.event.EventType
import org.ehcache.jsr107.Eh107Configuration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import javax.cache.CacheManager
import javax.cache.Caching
import javax.cache.spi.CachingProvider


@Configuration
@EnableCaching
class CacheConfig {

    //https://stackoverflow.com/questions/57909228/how-to-use-spring-boot-2-and-ehcache-3-without-xml
    //https://prohannah.tistory.com/88

    @Bean
    fun getCacheManager(): CacheManager {

        val provider: CachingProvider = Caching.getCachingProvider()
        val cacheManager: CacheManager = provider.cacheManager

        /**
         * heap은 JVM 힙 메모리에 캐시를 저장하도록 세팅하는 요소
        entries는 항목이다. 100개의 항목을 힙에 저장할 수 있다는 뜻
        100개가 넘어가면 가장 오랫동안 참조하지 않은 것을 삭제하고 새로운 것을 저장(LRU)
         */

        val configurationBuilder =
            CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String::class.java, String::class.java,
                ResourcePoolsBuilder.heap(100)
                    .offheap(10, MemoryUnit.MB) //offheap은 JVM 힙 메모리 외부의 메모리에 캐시를 저장하도록 세팅하는 요소이다.
            ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(30))) //ttl 설정


        /**
         * 리스너 클래스
         */

        val asynchronousListener: CacheEventListenerConfigurationBuilder =
            CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(
                    CacheEventLogger(), EventType.CREATED, EventType.EXPIRED
                ).unordered().asynchronous() //순서보장 X, 비동기 방식



        //create caches we need
        cacheManager.createCache(
            "simple-blog",
            Eh107Configuration.fromEhcacheCacheConfiguration(configurationBuilder.withService(asynchronousListener))
        )

        return cacheManager
    }


    class CacheEventLogger : CacheEventListener<Any, Any>{
        private val log = KotlinLogging.logger {  }

        override fun onEvent(event: CacheEvent<out Any, out Any>) {
            log.info("cache event logger message. getKey: {} / getOldValue: {} / getNewValue:{}", event.key, event.oldValue, event.newValue);
        }

    }



}