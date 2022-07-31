package com.example.simpleblog.domain.commenet

import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.post.Post


data class CommentSaveReq(
    val memberId:Long,
    val content:String,
    val postId: Long
){

}

fun CommentSaveReq.toEntity(post: Post): Comment {
    return Comment(
        content = this.content,
        post = post,
        member = Member.createFakeMember(this.memberId)
    )
}

