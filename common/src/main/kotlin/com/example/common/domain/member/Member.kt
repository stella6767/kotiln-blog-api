package com.example.common.domain.member

import com.example.common.domain.AuditingEntity
import com.example.web.dto.MemberRes
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

    fun toDto(): MemberRes {

        return MemberRes(
            id = this.id!!,
            email = this.email,
            password = this.password,
            role = this.role,
            createdAt = this.createAt,
            updateAt = this.updateAt
        )
    }

    override fun toString(): String {
        return "Member(id=$id, email='$email', password='$password', role=$role, createdAt=$createAt)"
    }

    companion object {
        fun createFakeMember(memberId:Long): Member {
            val member = Member(id=memberId, "admin@gmail.com", password = "1234")
            return member
        }
    }
}





enum class Role {
    USER, ADMIN
}


