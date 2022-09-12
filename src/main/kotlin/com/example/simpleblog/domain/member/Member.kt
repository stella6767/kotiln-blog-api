package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.post.Post
import javax.persistence.*


@Entity
@Table(name = "Member")
class Member(
        id:Long = 0,
        email:String,
        password:String,
        role: Role = Role.USER
): AuditingEntity(id) {

    @Column(name = "email", nullable = false)
    var email:String =email
        protected set

    @Column(name = "password")
    var password:String = password
        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

    override fun toString(): String {
        return "Member(id=$id, email='$email', password='$password', role=$role)"
    }

    companion object {
        fun createFakeMember(memberId:Long): Member {
            val member = Member(id=memberId, "admin@gmail.com", password = "1234")
            return member
        }
    }
}


fun Member.toDto(): MemberRes {
    return MemberRes(
        id = this.id!!,
        email = this.email,
        password = this.password,
        role = this.role
    )
}


enum class Role {
    USER, ADMIN
}


