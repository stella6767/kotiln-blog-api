package com.example.simpleblog.mvc.service



import com.example.simpleblog.core.domain.member.MemberRepository
import com.example.simpleblog.core.domain.member.MemberRes
import com.example.simpleblog.mvc.config.security.PrincipalDetails
import com.example.simpleblog.mvc.web.dto.LoginDto
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val memberRepository: MemberRepository,

    ) : UserDetailsService {

    val log = KotlinLogging.logger { }


    fun test(){


    }

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