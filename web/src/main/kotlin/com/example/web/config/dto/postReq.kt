package com.example.web.config.dto

import com.example.common.domain.member.Member
import com.example.common.domain.post.Post
import javax.validation.constraints.NotNull

data class PostSaveReq(

    @field:NotNull(message = "require title")
    val title:String?,
    val content:String?,

    @field:NotNull(message = "require memberId")
    val memberId: Long?
){
    fun toEntity(): Post {
        return Post(
            title = this.title ?: "",
            content = this.content ?: "",
            member = Member.createFakeMember(this.memberId!!)
        )
    }
}



