package com.example.simpleblog.mvc.web.dto

import com.example.simpleblog.core.domain.commenet.Comment
import com.example.simpleblog.core.domain.member.Member
import com.example.simpleblog.core.domain.post.Post


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


