package com.example.simpleblog.core.domain.post

import com.example.simpleblog.core.domain.member.MemberRes
import com.example.simpleblog.core.util.dto.BaseResponseDto


data class PostRes(
    val title:String,
    val content:String,
    val member: MemberRes
) : BaseResponseDto()