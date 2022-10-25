package com.example.simpleblog.mvc.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.auth0.jwt.interfaces.JWTVerifier
import com.example.simpleblog.mvc.config.properties.JwtProperties
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct


@Component
class JwtManager(
    val jwtProperties: JwtProperties
) {

    private val log = KotlinLogging.logger { }
    private val claimPrincipal = "principal"
    val authorizationHeader = "Authorization"
    val jwtHeader = "Bearer "
    private val jwtSubject = "my-token"

    @PostConstruct
    fun init(){
        log.info { "???? ${jwtProperties.access.secretKey}" }
    }

    fun generateRefreshToken(principal: String): String {
        val expireDate = Date(System.currentTimeMillis() + jwtProperties.refresh.getExpireDayToMillis())
        log.info { "refreshToken ExpireDate=>$expireDate" }
        return doGenerateToken(expireDate, principal, jwtProperties.refresh.secretKey)
    }


    fun generateAccessToken(principal: String): String {
        val expireDate = Date(System.currentTimeMillis() + jwtProperties.access.expireSecond)
        log.info { "accessToken ExpireDate=>$expireDate" }
        return doGenerateToken(expireDate, principal, jwtProperties.access.secretKey)
    }

    private fun doGenerateToken(
        expireDate: Date,
        principal: String,
        secretKey: String
    ) = JWT.create()
        .withSubject(jwtSubject)
        .withExpiresAt(expireDate)
        .withClaim(claimPrincipal, principal)
        .sign(Algorithm.HMAC512(secretKey))




    fun getPrincipalStringByAccessToken(accessToken: String): String {
        val decodedJWT = getDecodeJwt(secretKey = jwtProperties.access.secretKey, token = accessToken)
        return decodedJWT.getClaim(claimPrincipal).asString()
    }

    fun getPrincipalStringByRefreshToken(refreshToken: String): String {
        val decodedJWT = getDecodeJwt(secretKey = jwtProperties.refresh.secretKey, token = refreshToken)
        return decodedJWT.getClaim(claimPrincipal).asString()
    }


    private fun getDecodeJwt(secretKey: String, token: String): DecodedJWT {
        val verifier: JWTVerifier = JWT.require(Algorithm.HMAC512(secretKey))
            .build() //Reusable verifier instance
        val decodedJWT: DecodedJWT = verifier.verify(token)
        return decodedJWT
    }


    fun validAccessToken(token: String): TokenValidResult {
        return validatedJwt(token, jwtProperties.access.secretKey)
    }

    fun validRefreshToken(token: String): TokenValidResult {
        return validatedJwt(token, jwtProperties.refresh.secretKey)
    }


    private fun validatedJwt(token: String, secretKey: String): TokenValidResult { // TRUE |  JWTVerificationException
        return try {
            getDecodeJwt(secretKey, token)
            TokenValidResult.Success()
        } catch (e: JWTVerificationException) {
            //Invalid signature/claims
            //log.error { "error=>${e.stackTraceToString()}" }
            TokenValidResult.Failure(e)
        }
    }

}


/**
 * 코틀린으로 Union Type 같이 흉내
 */


sealed class TokenValidResult {
    class Success(val successValue: Boolean = true) : TokenValidResult()
    class Failure(val exception: JWTVerificationException) : TokenValidResult()
}


