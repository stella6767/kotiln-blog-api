package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRes
import com.example.simpleblog.util.dto.BaseDto
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





data class PostRes(
    val title:String,
    val content:String,
    val member: MemberRes
) : BaseDto()