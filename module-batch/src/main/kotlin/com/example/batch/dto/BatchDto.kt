package com.example.batch.dto

import com.example.simpleblog.core.domain.member.Role
import java.time.LocalDateTime
import javax.persistence.Column


data class MemberWithPost(
    val id:String,
    val email: String,
    val password: String,
    val role: String,
    var createAt: String,
    val updateAt: String,
    val deleteAt: String?,
    val orderNo: Long?,
    //val postTitles:List<String>,
)
