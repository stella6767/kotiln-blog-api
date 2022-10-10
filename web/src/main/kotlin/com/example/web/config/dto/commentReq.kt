package com.example.web.config.dto

import com.example.common.domain.commenet.Comment
import com.example.common.domain.member.Member
import com.example.common.domain.post.Post

data class CommentSaveReq(
    val memberId:Long,
    val content:String,
    val postId: Long
){
    fun toEntity(post: Post): Comment {
        return Comment(
            content = this.content,
            post = post,
            member = Member.createFakeMember(this.memberId)
        )
    }
}

