package com.example.simpleblog.config.security

import com.example.simpleblog.domain.HashMapRepositoryImpl
import com.example.simpleblog.domain.InMemoryRepository
import com.example.simpleblog.domain.member.LoginDto
import com.example.simpleblog.util.CookieProvider
import com.example.simpleblog.util.CookieProvider.CookieName
import com.example.simpleblog.util.Script

import com.example.simpleblog.util.value.CmResDto
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.concurrent.TimeUnit
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomUserNameAuthenticationFilter(
    private val om: ObjectMapper,
    private val memoryRepository: InMemoryRepository,
) : UsernamePasswordAuthenticationFilter() {

    private val log = KotlinLogging.logger {  }
    private val jwtManager = JwtManager()

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        log.debug { "login 요청 옴" }
        lateinit var loginDto:LoginDto
        try {
            loginDto = om.readValue(request?.inputStream, LoginDto::class.java)
            log.debug { "login Dto : $loginDto" }
        }catch (e:Exception){
            log.error { "loginFilter: 로그인 요청 Dto 생성 중 실패!    ${e.stackTraceToString()}" }
        }

        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.rawPassword)
        return this.authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {

        log.debug { "로그인 완료되어서 JWT 토큰 만들어서 response " }

        val principalDetails = authResult?.principal as PrincipalDetails
        val accessToken = jwtManager.generateAccessToken(om.writeValueAsString(principalDetails))
        val refreshToken = jwtManager.generateRefreshToken(om.writeValueAsString(principalDetails))

        val refreshCookie = CookieProvider.createCookie(
            CookieName.REFRESH_COOKIE,
            refreshToken,
            TimeUnit.DAYS.toSeconds(jwtManager.refreshTokenExpireDay)
        )

        response?.addHeader(jwtManager.authorizationHeader, jwtManager.jwtHeader + accessToken)
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString())
        memoryRepository.save(refreshToken, om.writeValueAsString(principalDetails) )
        val jsonResult = om.writeValueAsString(CmResDto(HttpStatus.OK, "login success", principalDetails.member))
        Script.responseData(response, jsonResult)
    }
}