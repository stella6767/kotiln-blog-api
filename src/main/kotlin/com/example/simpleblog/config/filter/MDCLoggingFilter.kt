package com.example.simpleblog.config.filter

import mu.KotlinLogging
import org.slf4j.MDC
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class MDCLoggingFilter : Filter{

    val log = KotlinLogging.logger {  }

    /**
     * 인증처리를 하고 싶다.
     */

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val uuid = UUID.randomUUID()
        MDC.put("request_id", uuid.toString())
        chain.doFilter(request, response)
        MDC.clear()
    }
}