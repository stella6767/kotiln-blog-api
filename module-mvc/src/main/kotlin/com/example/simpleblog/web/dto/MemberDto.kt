package com.example.simpleblog.web.dto

import com.example.simpleblog.config.BeanAccesseor
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.Role
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.NotNull


/**
 * dto <=> entity 간의 맵핑할 때, 크게 스타일이 2개 있는 거 같더라구요.
 *
 * 1. 각 dto, entity에 책임 할당
 * 2. entitymapper라는 놈을 하나 만들어서 개가 담당하게끔 하는 스타일도 있는데
 *
 */


data class LoginDto(
    @field:NotNull(message = "require email")
    val email:String?,
    val rawPassword:String?,
    val role: Role?
){

    fun toEntity(): Member {
        return Member(
            email = this.email ?: "",
            password = encodeRawPassword(),
            role = this.role ?: Role.USER,
        )
    }


    private fun encodeRawPassword(): String =
        BeanAccesseor.getBean(BCryptPasswordEncoder::class )
            .encode(this.rawPassword)

}




