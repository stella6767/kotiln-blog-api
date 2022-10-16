package com.example.simpleblog.domain.commenet

import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRes
import com.example.simpleblog.domain.post.Post
import com.example.simpleblog.util.dto.BaseDto
import java.util.*
import javax.persistence.FetchType
import javax.persistence.ManyToOne


data class CommentSaveReq(
    val memberId:Long,
    val content:String,
    val postId: Long,
    val idAncestor:Long?
){

    fun toEntity(post: Post): Comment {

        val comment = Comment(
            content = this.content,
            post = post,
            member = Member.createFakeMember(this.memberId)
        )

        return comment
    }

}



data class CommentRes(
    val content: String,
    val member: MemberRes
): BaseDto()

