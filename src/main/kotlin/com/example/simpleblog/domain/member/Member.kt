package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import javax.persistence.*


@Entity
@Table(name = "Member")
class Member(
        email:String,
        password:String,
        role: Role = Role.USER
): AuditingEntity() {

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
        return "Member(email='$email', password='$password', role=$role)"
    }

    companion object Factory {
        fun createMember(memberId:Long): Member {
            val member = Member("", "")
            member.id = memberId
            return member
        }
    }


}


enum class Role {
    USER, ADMIN
}


