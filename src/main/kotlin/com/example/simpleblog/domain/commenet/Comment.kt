package com.example.simpleblog.domain.commenet

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.post.Post
import javax.persistence.*


@Entity
@Table(name = "Comment")
class Comment (
        title:String,
        content:String,
        post: Post
) : AuditingEntity() {

    @Column(name = "content", nullable = false)
    var content:String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post::class)
    var post: Post = post
        protected set


}
