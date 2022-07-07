package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.AuditingEntity
import javax.persistence.*


@Entity
@Table(name = "Member")
class Member(
        email:String,
        password:String,
        role: Role
): AuditingEntity() {

    @Column(name = "title", nullable = false)
    var email:String =email
        protected set

    @Column(name = "content")
    var password:String = password
        protected set

    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

}


enum class Role {
    USER, ADMIN
}


