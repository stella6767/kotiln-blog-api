package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.member.Member
import java.time.LocalDateTime

data class PostRes(
        val title:String,
        val content: String?,
        val createAt: LocalDateTime
)


data class PostSaveReq(
        val memberId:Long,
        val title: String,
        val content: String? = null
){

    fun toEntity():Post{
        val member = Member.createMember(this.memberId)
        return Post(
                title=this.title,
                content=this.content,
                member = member
        )
    }

}
