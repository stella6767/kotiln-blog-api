package com.example.simpleblog.mvc.service



import com.example.simpleblog.mvc.config.redis.repo.InMemoryService
import com.example.simpleblog.mvc.setup.TestRedisConfiguration
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@Import(TestRedisConfiguration::class)
class RedisRepositoryTest {

    @Autowired
    private lateinit var redisRepositoryImpl: InMemoryService



    private val log = KotlinLogging.logger {  }


    @Test
    fun setup(){
        log.info { "test setup==> ${this.redisRepositoryImpl}" }
    }


    //@Test
    fun redisRepoTest(){

        val numberOfThreads = 1000
        val service = Executors.newFixedThreadPool(10)
        val latch = CountDownLatch(numberOfThreads)
        for (index in 1..numberOfThreads){
            service.submit{
                this.redisRepositoryImpl.save(index.toString(), index)
                latch.countDown()
            }
            //this.redisRepositoryImpl.save(index.toString(), index)
        }

        latch.await()
        val value = this.redisRepositoryImpl.findByKey(1.toString())
        println(value)
        val results = this.redisRepositoryImpl.findAll()
        for (result in results) {
            println("result===>" + result.toString())
        }
        Assertions.assertThat(results.size).isEqualTo(numberOfThreads)
    }


//    @TestConfiguration
//    class config(
//        @Autowired
//        val redisTemplate: RedisTemplate<String, Any>
//    ) {
//        @Bean
//        fun inMemoryRepository(): InMemoryRepository{
//            return RedisRepositoryImpl(redisTemplate)
//        }
//    }



}