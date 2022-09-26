package com.example.simpleblog.util

import mu.KotlinLogging
import org.springframework.http.ResponseCookie
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

object CookieProvider {

    private val log = KotlinLogging.logger {  }

    fun createNullCookie(cookieName:String):String{
        TODO()
    }


    fun createCookie(cookieName: String, value:String, maxAge:Long): ResponseCookie {

        return ResponseCookie.from(cookieName, value)
            .httpOnly(true)
            .secure(false) //http 허용
            .path("/")
            .maxAge(maxAge)
            .build()
    }


    fun getCookie(req:HttpServletRequest, cookieName: String): Optional<String> {

        val cookieValue = req.cookies.filter { cookie ->
            cookie.name == cookieName
        }.map { cookie ->
            cookie.value
        }.firstOrNull()

        log.info { "cookieValue==> $cookieValue" }

        return Optional.ofNullable(cookieValue)
    }




}