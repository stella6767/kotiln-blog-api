package com.example.simpleblog.domain.member

import java.time.LocalDateTime


data class JoinReq(
        val email:String,
        val password:String
){

    /**
     * dto <=> entity 변환 스타일이 크게 2가지 있는 것 같더라구요.
     */

    fun toEntity(): Member {
        return Member(
                email = this.email,
                password = this.password,
                role = Role.USER
        )
    }
}


data class MemberRes(
        val id:Long?,
        val email: String,
        val password: String,
        val role: Role,
        val creatAt:LocalDateTime
)

