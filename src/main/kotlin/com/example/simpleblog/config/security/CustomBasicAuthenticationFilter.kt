package com.example.simpleblog.config.security

import com.auth0.jwt.exceptions.TokenExpiredException
import com.example.simpleblog.domain.member.MemberRepository
import com.example.simpleblog.util.CookieProvider
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomBasicAuthenticationFilter(
    private val memberRepository: MemberRepository,
    private val om: ObjectMapper,
    authenticationManager: AuthenticationManager
) : BasicAuthenticationFilter(authenticationManager) {

    val log = KotlinLogging.logger { }
    private val jwtManager = JwtManager()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        log.info { "권한이나 인증이 필요한 요청이 들어옴" }

        val accessToken = request.getHeader(jwtManager.authorizationHeader)?.replace("Bearer ", "")
        if (accessToken == null) {
            log.info { "token이 없슴" }
            chain.doFilter(request, response)
            return
        }

        log.debug { "access token: $accessToken" }

        val accessTokenResult: TokenValidResult = jwtManager.validAccessToken(accessToken)


        if (accessTokenResult is TokenValidResult.Failure) {
            handleTokenException(accessTokenResult) {

                log.info { "getClass==>${accessTokenResult.exception.javaClass}" }
                val refreshToken =
                    CookieProvider.getCookie(request, CookieProvider.CookieName.REFRESH_COOKIE).orElseThrow()
                val refreshTokenResult = jwtManager.validRefreshToken(refreshToken)
                if (refreshTokenResult is TokenValidResult.Failure) {
                    throw RuntimeException("invalid refreshToken")
                }
                val princpalString = jwtManager.getPrincipalStringByRefreshToken(refreshToken)
                val details = om.readValue(princpalString, PrincipalDetails::class.java)

                reissueAccessToken(details, response)
                setAuthentication(details, chain, request, response)
            }
            return
        }


        val principalJsonData = jwtManager.getPrincipalStringByAccessToken(accessToken)
        val principalDetails = om.readValue(principalJsonData, PrincipalDetails::class.java)

        setAuthentication(principalDetails, chain, request, response)
    }

    private fun setAuthentication(
        details: PrincipalDetails,
        chain: FilterChain,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val authentication: Authentication = UsernamePasswordAuthenticationToken(
            details,
            details.password,
            details.authorities
        )
        SecurityContextHolder.getContext().authentication = authentication //인증 처리 끝
        chain.doFilter(request, response)
    }

    private fun reissueAccessToken(
        details: PrincipalDetails?,
        response: HttpServletResponse
    ) {
        log.info { "accessToken 재발급" }

        val accessToken = jwtManager.generateAccessToken(om.writeValueAsString(details))
        response?.addHeader(jwtManager.authorizationHeader, jwtManager.jwtHeader + accessToken)
    }


    private fun handleTokenException(tokenValidResult: TokenValidResult.Failure, func: () -> Unit) {
        when (tokenValidResult.exception) {
            is TokenExpiredException -> func()
            else -> {
                log.info { "여기 타는지 체크" }
                log.error(tokenValidResult.exception.stackTraceToString())
                throw tokenValidResult.exception
            }
        }

    }


}