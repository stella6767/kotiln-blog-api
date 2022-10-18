package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import javax.persistence.*


@Entity
@Table(name = "Member")
class Member(
    id: Long = 0,
    email: String,
    password: String,
    role: Role = Role.USER
) : AuditingEntity(id) {

    @Column(name = "email", nullable = false)
    var email: String = email
        protected set

    @Column(name = "password")
    var password: String = password
        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

    fun toDto(): MemberRes {
        val dto = MemberRes(
            email = this.email,
            password = this.password,
            role = this.role,
        )
        setBaseDtoProperty(dto)
        return dto
    }

    override fun toString(): String {
        return "Member(id=$id, email='$email', password='$password', role=$role, createdAt=$createAt, updatedAt=$updateAt)"
    }

    companion object {
        fun createFakeMember(memberId: Long): Member {
            val member = Member(id = memberId, "admin@gmail.com", password = "1234")
            return member
        }
    }
}


enum class Role {
    USER, ADMIN
}


