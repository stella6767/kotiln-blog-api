package com.example.web.dto

import com.example.common.domain.member.Member
import com.example.common.domain.member.Role

import java.time.LocalDateTime
import javax.validation.constraints.NotNull


/**
 * dto <=> entity 간의 맵핑할 때, 크게 스타일이 2개 있는 거 같더라구요.
 *
 * 1. 각 dto, entity에 책임 할당
 * 2. entitymapper라는 놈을 하나 만들어서 개가 담당하게끔 하는 스타일도 있는데
 *
 */




data class MemberRes(
    val id:Long,
    val email:String,
    val password:String,
    val role: Role,
    val createdAt:LocalDateTime,
    val updateAt:LocalDateTime,
)


