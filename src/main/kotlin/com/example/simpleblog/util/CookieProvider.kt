package com.example.simpleblog.util

import mu.KotlinLogging
import org.springframework.http.ResponseCookie
import java.util.*
import javax.servlet.http.HttpServletRequest

object CookieProvider {

    private val log = KotlinLogging.logger {  }

    fun createNullCookie(cookieName:String):String{
        TODO()
    }


    fun createCookie(cookieName: CookieName, value:String, maxAge:Long): ResponseCookie {

        return ResponseCookie.from(cookieName.name, value)
            .httpOnly(true)
            .secure(false) //http 허용
            .path("/")
            .maxAge(maxAge)
            .build()
    }


    fun getCookie(req:HttpServletRequest, cookieName: CookieName): Optional<String> {

        val cookieValue = req.cookies.filter { cookie ->
            cookie.name == cookieName.name
        }.map { cookie ->
            cookie.value
        }.firstOrNull()

        log.info { "cookieValue==> $cookieValue" }

        return Optional.ofNullable(cookieValue)
    }


    enum class CookieName(

    ){
        REFRESH_COOKIE
    }

}