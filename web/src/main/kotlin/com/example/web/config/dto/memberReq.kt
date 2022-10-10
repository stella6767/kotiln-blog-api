package com.example.web.config.dto

import com.example.common.domain.member.Member
import com.example.common.domain.member.Role
import com.example.web.config.BeanAccesseor
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.NotNull

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

