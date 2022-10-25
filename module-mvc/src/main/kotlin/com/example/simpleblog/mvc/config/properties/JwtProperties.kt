package com.example.simpleblog.mvc.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.concurrent.TimeUnit


@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val access: Access,
    val refresh: Refresh
){

    class Access(
        val secretKey: String,
        expireSecond:Long
    ){
        val expireSecond:Long = TimeUnit.SECONDS.toMillis(expireSecond)
    }

    class Refresh(
        val secretKey: String,
        expireDay:Long
    ){

        private val expireDay = expireDay

        fun getExpireDayToMillis(): Long {
            return TimeUnit.DAYS.toMillis(this.expireDay)
        }

        fun getExpireDayToSeconds(): Long {
            return TimeUnit.DAYS.toSeconds(this.expireDay)
        }

    }

}
