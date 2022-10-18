package com.example.simpleblog.mvc.web.dto


import com.example.simpleblog.core.domain.member.Member
import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.domain.post.PostType
import java.time.LocalDateTime
import javax.validation.constraints.NotNull


data class PostSaveReq(

    @field:NotNull(message = "require title")
    val title:String?,
    val content:String?,
    @field:NotNull(message = "require memberId")
    val memberId: Long?,
    val reservateAt: LocalDateTime,
    val postType: PostType,
){

    fun toEntity(): Post {

        return Post(
            title = this.title ?: "",
            content = this.content ?: "",
            member = Member.createFakeMember(this.memberId!!),
            reservateAt = this.reservateAt,
            postType = this.postType
        )
    }


}


