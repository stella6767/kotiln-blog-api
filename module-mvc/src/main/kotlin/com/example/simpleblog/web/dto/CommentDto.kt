package com.example.simpleblog.web.dto

import com.example.simpleblog.domain.commenet.Comment
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.post.Post


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


