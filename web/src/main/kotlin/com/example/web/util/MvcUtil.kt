package com.example.web.util

import mu.KotlinLogging
import org.springframework.web.context.request.ServletRequestAttributes

import javax.servlet.http.HttpServletRequest

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

//https://stackoverflow.com/questions/592123/is-there-a-static-way-to-get-the-current-httpservletrequest-in-spring

object MvcUtil {


    private val log = KotlinLogging.logger {  }

    fun getCurrentHttpRequest(): HttpServletRequest {
        val requestAttributes = RequestContextHolder.getRequestAttributes()
        if (requestAttributes is ServletRequestAttributes) {
            return requestAttributes.request
        }
        log.debug("Not called in the context of an HTTP request")
        throw RuntimeException("not found request")
    }


    fun getBaseUrl(): String {

        //https://stackoverflow.com/questions/5012525/get-root-base-url-in-spring-mvc

        val request = getCurrentHttpRequest()

         val baseUrl2 =
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

        val baseUrl3 = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null)


        val baseUrl = request?.localAddr + ":" + request?.localPort +  request?.servletPath


        log.info { """
            
            ${request?.serverName}
            ${request?.servletPath}
            ${request?.serverPort}
            ${request?.remoteAddr}
            ${request?.remoteHost}
            ${request?.remotePort}
            ${baseUrl2}
            ${baseUrl}
                       
        """.trimIndent() }

        //https://stackoverflow.com/questions/40401383/spring-boot-get-application-base-url-outside-of-servlet-context

        return baseUrl3.toUriString()
    }



}