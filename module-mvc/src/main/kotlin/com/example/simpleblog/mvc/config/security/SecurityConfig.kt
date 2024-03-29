package com.example.simpleblog.mvc.config.security

import com.example.simpleblog.core.domain.member.MemberRepository
import com.example.simpleblog.mvc.config.redis.repo.InMemoryService
import com.example.simpleblog.mvc.config.redis.repo.RedisServiceImpl
import com.example.simpleblog.mvc.util.CookieProvider
import com.example.simpleblog.mvc.util.Script
import com.example.simpleblog.mvc.web.dto.common.CmResDto
import com.fasterxml.jackson.databind.ObjectMapper
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val objectMapper: ObjectMapper,
    private val memberRepository: MemberRepository,
    private val redisTemplate: RedisTemplate<String, Any>,
    private val jwtManager: JwtManager
)  {

    private val log = KotlinLogging.logger {  }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer {
                web: WebSecurity -> web.ignoring().antMatchers("/**")
        }
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors().configurationSource(corsConfig())
            .and()
            .addFilter(loginFilter())
            .addFilterBefore(authenticationFilter(), BasicAuthenticationFilter::class.java)
            .exceptionHandling()
            .accessDeniedHandler(CustomAccessDeniedHandler())
            .authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))
            .and()
            .authorizeRequests()
            //.antMatchers("/**").authenticated()
            .antMatchers("/v1/posts").hasAnyRole("USER","ADMIN")
            //.antMatchers("/v1/member/profile").hasAnyRole("USER","ADMIN")
            .anyRequest().permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(CustomLogoutSuccessHandler(objectMapper))

        return http.build()
    }


    class CustomLogoutSuccessHandler(
        private val om:ObjectMapper
    ):LogoutSuccessHandler {

        private val log = KotlinLogging.logger {  }

        override fun onLogoutSuccess(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authentication: Authentication?
        ) {

            log.info { "logout success" }
            val context = SecurityContextHolder.getContext()
            context.authentication = null
            SecurityContextHolder.clearContext()


            val nullCookie = CookieProvider.createNullCookie(CookieProvider.CookieName.REFRESH_COOKIE)

            response.addHeader(HttpHeaders.SET_COOKIE, nullCookie.toString())
            val cmResDto = CmResDto(HttpStatus.OK, "logout success", null)
            Script.responseData(response, om.writeValueAsString(cmResDto))
        }

    }



    class CustomAuthenticationEntryPoint(
        private val objectMapper: ObjectMapper
    ) : AuthenticationEntryPoint {

        private val log = KotlinLogging.logger {  }

        override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
        ) {
            log.info { "??? access denied!!!!" }
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.reasonPhrase)
        }

    }


    class CustomFailureHandler : AuthenticationFailureHandler {

        private val log = KotlinLogging.logger {  }

        override fun onAuthenticationFailure(
            request: HttpServletRequest?,
            response: HttpServletResponse,
            exception: AuthenticationException?
        ) {
            log.warn { "로그인 실패!!!!!!" }
            response.sendError(HttpServletResponse.SC_FORBIDDEN, " 인증 실패")
        }

    }


    class CustomSuccessHandler : AuthenticationSuccessHandler {

        private val log = KotlinLogging.logger {  }

        override fun onAuthenticationSuccess(
            request: HttpServletRequest?,
            response: HttpServletResponse?,
            authentication: Authentication?
        ) {

            log.info { "login Success!!!!" }

        }

    }

    class CustomAccessDeniedHandler : AccessDeniedHandler {

        private val log = KotlinLogging.logger {  }

        override fun handle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            accessDeniedException: AccessDeniedException?
        ) {

            log.info { "why?? access denied!!!!" }
            response.sendError(HttpServletResponse.SC_FORBIDDEN)
        }
    }


    @Bean
    fun inmemoryRepository(): InMemoryService {
        return RedisServiceImpl(this.redisTemplate)
    }




    @Bean
    fun authenticationFilter(): CustomBasicAuthenticationFilter {
        return CustomBasicAuthenticationFilter(
            authenticationManager = authenticationManager(),
            memberRepository = memberRepository,
            om = objectMapper,
            memoryRepository = inmemoryRepository(),
            jwtManager = jwtManager
        )
    }


    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }


    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun loginFilter(): UsernamePasswordAuthenticationFilter {

        val authenticationFilter = CustomUserNameAuthenticationFilter(objectMapper, inmemoryRepository(),jwtManager = jwtManager)
        authenticationFilter.setAuthenticationManager(authenticationManager())
        authenticationFilter.setFilterProcessesUrl("/login")
        authenticationFilter.setAuthenticationFailureHandler(CustomFailureHandler())
        authenticationFilter.setAuthenticationSuccessHandler(CustomSuccessHandler())

        return authenticationFilter
    }


    @Bean
    fun corsConfig(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")
        config.addExposedHeader("authorization")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }



}