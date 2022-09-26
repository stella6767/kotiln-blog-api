package com.example.simpleblog.config.security

import com.example.simpleblog.domain.member.MemberRepository
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
        val token = request.getHeader(jwtManager.authorizationHeader)?.replace("Bearer ", "")

        if (token == null) {
            log.info { "token이 없슴" }
            chain.doFilter(request, response)
            return
        }

        log.debug { "token: $token" }
        val principalJsonData = jwtManager.getPrincipalStringByAccessToken(token) ?: throw RuntimeException("memberEmail을 찾을 수 없습니다")

        val principalDetails = om.readValue(principalJsonData, PrincipalDetails::class.java)
        //DB로 호출하잖아요.
        //val member = memberRepository.findMemberByEmail(details.member.email)
        //val principalDetails = PrincipalDetails(member)

        //요게 문제였다.
        val authentication:Authentication = UsernamePasswordAuthenticationToken(
            principalDetails,
            principalDetails.password,
            principalDetails.authorities
        )

        SecurityContextHolder.getContext().authentication = authentication //인증 처리 끝
        chain.doFilter(request, response)
    }


}