package com.example.simpleblog.domain.commenet

import com.example.simpleblog.domain.member.MemberRes
import com.example.simpleblog.util.dto.BaseResponseDto


data class CommentRes(
    val content: String,
    val member: MemberRes
): BaseResponseDto()

