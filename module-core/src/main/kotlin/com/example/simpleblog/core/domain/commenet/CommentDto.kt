package com.example.simpleblog.core.domain.commenet

import com.example.simpleblog.core.domain.member.MemberRes
import com.example.simpleblog.core.util.dto.BaseResponseDto


data class CommentRes(
    val content: String,
    val member: MemberRes
): BaseResponseDto()

