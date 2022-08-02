package com.example.simpleblog.util

import com.example.simpleblog.config.security.JwtManager
import com.example.simpleblog.config.security.PrincipalDetails
import com.example.simpleblog.domain.member.Member
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UtilTest {

    private val log = KotlinLogging.logger {  }


    @Test
    fun errorLogTest(){

        //throw RuntimeException("error")
        log.error { "error!!" }
    }

    @Test
    fun bcryptEncodeTest(){

        val encoder = BCryptPasswordEncoder()

        val encpassword = encoder.encode("1234")

        log.info { encpassword }

    }


    @Test
    fun generateJwtTest(){

        val jwtManager = JwtManager()

        val details = PrincipalDetails(Member.createFakeMember(1))
        val accessToken = jwtManager.generateAccessToken(details)

        val email = jwtManager.getMemberEmail(accessToken)

        log.info { "accessToken $accessToken" }
        log.info { "email $email" }

    }


}