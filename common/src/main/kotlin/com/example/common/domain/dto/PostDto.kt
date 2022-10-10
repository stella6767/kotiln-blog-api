package com.example.common.domain.dto


import com.example.common.domain.member.Member
import com.example.common.domain.post.Post
import com.example.web.dto.MemberRes
import javax.validation.constraints.NotNull




data class PostRes(
    val id:Long,
    val title:String,
    val content:String,
    val member: MemberRes
)