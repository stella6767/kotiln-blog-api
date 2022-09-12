package com.example.simpleblog.config.security

import com.example.simpleblog.domain.member.LoginDto
import com.example.simpleblog.util.func.responseData
import com.example.simpleblog.util.value.CmResDto
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomUserNameAuthenticationFilter(
    private val om: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    private val log = KotlinLogging.logger {  }
    private val jwtManager = JwtManager()

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        log.info { "login 요청 옴" }
//        request.getAttribute("email")

        lateinit var loginDto:LoginDto

        try {
            loginDto = om.readValue(request?.inputStream, LoginDto::class.java)
            log.info { "login Dto : $loginDto" }
        }catch (e:Exception){
            log.error { "loginFilter: 로그인 요청 Dto 생성 중 실패!    ${e.stackTraceToString()}" }
        }

        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)

        return this.authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {

        log.info { "로그인 완료되어서 JWT 토큰 만들어서 response " }

        val principalDetails = authResult?.principal as PrincipalDetails
        val jwtToken = jwtManager.generateAccessToken(om.writeValueAsString(principalDetails))

        response?.addHeader(jwtManager.authorizationHeader, jwtManager.jwtHeader + jwtToken)

        val jsonResult = om.writeValueAsString(CmResDto(HttpStatus.OK, "login success", principalDetails.member))

        responseData(response, jsonResult)
    }
}