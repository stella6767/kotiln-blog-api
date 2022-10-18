package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.member.MemberRes
import com.example.simpleblog.util.dto.BaseResponseDto


data class PostRes(
    val title:String,
    val content:String,
    val member: MemberRes
) : BaseResponseDto()