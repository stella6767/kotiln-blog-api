package com.example.web.util

import com.example.web.config.security.JwtManager
import com.example.web.config.security.PrincipalDetails
import com.example.web.config.redis.HashMapRepositoryImpl
import com.example.web.config.redis.InMemoryRepository
import com.example.common.domain.member.Member
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class UtilTest {

    private val log = KotlinLogging.logger {  }

    val mapper = ObjectMapper()


    @Test
    fun hashMapRepoTest(){

        val repo: InMemoryRepository = HashMapRepositoryImpl()

        val numberOfThreads = 1000

        val service = Executors.newFixedThreadPool(10)
        val latch = CountDownLatch(numberOfThreads)

        for (index in 1..numberOfThreads){
            service.submit{
                repo.save(index.toString(), index)
                latch.countDown()
            }
        }

        latch.await()

        Thread.sleep(1000)

        val results = repo.findAll()
        Assertions.assertThat(results.size).isEqualTo(numberOfThreads)

    }


    @Test
    fun errorLogTest(){

        //throw RuntimeException("error")
        log.error { "error!!" }
    }

    @Test
    fun bcryptEncodeTest(){

        val encoder = BCryptPasswordEncoder()

        val encpassword = encoder.encode("1234")

        val matches:Boolean = encoder.matches("1234", "\$2a\$10\$5pHCXu/JF3075RUiZ6Ina.ZG3TeuOPH6NB0/LrMWL7XLvu2rNJ3Ty")

        log.info { matches }
        log.info { encpassword }

    }


    @Test
    fun generateJwtTest(){

        mapper.registerModule(JavaTimeModule())
        mapper.registerModule(
            KotlinModule.Builder()
                .configure(KotlinFeature.StrictNullChecks, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .build()
        )
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        mapper.registerModule(CoreJackson2Module())
//        mapper.addMixIn(Member.javaClass, PrincipalDetails::class.java)
//        val deserialization = SimpleModule()
//        deserialization.addDeserializer(PrincipalDetails::class.java, PrincipalDetails())
//        mapper.registerModule(deserialization)

//        var simpleModule = SimpleModule()
//            .addAbstractTypeMapping(UserDetails.class, PrincipalDetails.class )


        val jwtManager = JwtManager(accessTokenExpireSecond = 1)


        val details = PrincipalDetails(Member.createFakeMember(1))
        val jsonPrincipal = mapper.writeValueAsString(details)
        val accessToken = jwtManager.generateAccessToken(jsonPrincipal)


        Thread.sleep(3000)

//        val decodedJWT = jwtManager.validatedJwt(accessToken)
//
//        val principalString = decodedJWT.getClaim(jwtManager.claimPrincipal).asString()
//        val principalDetails: PrincipalDetails = mapper.readValue(principalString, PrincipalDetails::class.java)
//
//        log.info { "result=>${principalDetails.member}" }
//
//        principalDetails.authorities.forEach {
//            println(it.authority)
//        }
//
//        details.authorities.forEach {
//            println(it.authority)
//        }
    }






}