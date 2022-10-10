package com.example.common.domain.commenet

import com.example.common.domain.AuditingEntity
import com.example.common.domain.member.Member
import com.example.common.domain.post.Post
import javax.persistence.*


@Entity
@Table(name = "Comment")
class Comment (
    id:Long = 0,
    content:String,
        post: Post,
        member: Member
) : AuditingEntity(id) {

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
        return "Comment(id=$id, content='$content', post=$post, member=$member)"
    }


}
