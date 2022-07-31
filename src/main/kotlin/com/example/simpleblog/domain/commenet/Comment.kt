package com.example.simpleblog.domain.commenet

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.post.Post
import javax.persistence.*


@Entity
@Table(name = "Comment")
class Comment (
        content:String,
        post: Post,
        member: Member
) : AuditingEntity() {

    @Column(name = "content", nullable = false, length = 1000)
    var content:String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post::class)
    var post: Post = post
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        protected set

    override fun toString(): String {
        return "Comment(content='$content', post=$post, member=$member)"
    }


}
