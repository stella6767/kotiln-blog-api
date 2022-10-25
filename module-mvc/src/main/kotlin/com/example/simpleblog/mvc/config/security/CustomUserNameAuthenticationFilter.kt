package com.example.simpleblog.mvc.config.security


import com.example.simpleblog.mvc.config.redis.repo.InMemoryService
import com.example.simpleblog.mvc.util.CookieProvider
import com.example.simpleblog.mvc.util.CookieProvider.CookieName
import com.example.simpleblog.mvc.util.Script
import com.example.simpleblog.mvc.web.dto.LoginDto
import com.example.simpleblog.mvc.web.dto.common.CmResDto


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
    private val memoryRepository: InMemoryService,
    private val jwtManager: JwtManager
) : UsernamePasswordAuthenticationFilter() {

    private val log = KotlinLogging.logger {  }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {

        log.debug { "login 요청 옴" }
        lateinit var loginDto: LoginDto
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

        val principalDetails = authResult.principal as PrincipalDetails
        val accessToken = jwtManager.generateAccessToken(om.writeValueAsString(principalDetails))
        val refreshToken = jwtManager.generateRefreshToken(om.writeValueAsString(principalDetails))

        val refreshCookie = CookieProvider.createCookie(
            CookieName.REFRESH_COOKIE,
            refreshToken,
            jwtManager.jwtProperties.refresh.getExpireDayToSeconds()
        )

        response.addHeader(jwtManager.authorizationHeader, jwtManager.jwtHeader + accessToken)
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString())
        memoryRepository.save(refreshToken, om.writeValueAsString(principalDetails) )
        val jsonResult = om.writeValueAsString(CmResDto(HttpStatus.OK, "login success", principalDetails.member))
        Script.responseData(response, jsonResult)
    }
}