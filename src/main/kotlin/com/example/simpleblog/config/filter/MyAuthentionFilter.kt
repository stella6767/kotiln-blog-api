package com.example.simpleblog.config.filter

import mu.KotlinLogging
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class MyAuthentionFilter : Filter{

    val log = KotlinLogging.logger {  }

    /**
     * 인증처리를 하고 싶다.
     */

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val servletRequest = request as HttpServletRequest
        val principal = servletRequest.session.getAttribute("principal")

        if (principal == null){
            throw RuntimeException("session not found!!")
        }else{
            chain?.doFilter(request, response)
        }
    }
}