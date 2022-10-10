package com.example.web.service

import com.example.web.config.security.PrincipalDetails
import com.example.common.domain.member.MemberRepository
import com.example.web.config.dto.LoginDto
import com.example.web.dto.MemberRes

import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class AuthService(
    private val memberRepository: MemberRepository,

) : UserDetailsService {

    val log = KotlinLogging.logger { }

    override fun loadUserByUsername(email: String): UserDetails {
        log.info { "loadUserByUsername 호출!" }
        val member = memberRepository.findMemberByEmail(email)

        log.info { "???????=>$member" }

        return PrincipalDetails(member)
    }

    @Transactional
    fun saveMember(dto: LoginDto): MemberRes {
        return memberRepository.save(dto.toEntity()).toDto()
    }


}