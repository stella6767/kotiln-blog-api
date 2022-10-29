package com.example.simpleblog.core.domain.member

import com.example.simpleblog.core.domain.AuditingEntity
import com.example.simpleblog.core.domain.member.Member.Role
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "BatchMember")
class BatchMember(
    id: Long = 0,
    email: String,
    password: String,
    role: Role = Role.USER,
    postTitles: MutableList<String> = mutableListOf<String>()
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

    @Convert(converter = PostTitlesConverter::class)
    @Column(name = "postTitles")
    var postTitles:MutableList<String> = postTitles


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
        return "BatchMember(id=$id, email='$email', password='$password', role=$role, postTitles=$postTitles)"
    }

    companion object {

        fun of(
            id: Long = 0,
            createAt:LocalDateTime,
            deletedAt: LocalDateTime,
            updateAt:LocalDateTime,
            orderNo:Long,
            email: String,
            password: String,
            role: Role = Role.USER,
            postTitles: MutableList<String> = mutableListOf<String>()
        ): BatchMember {
            val member = BatchMember(
                id, email, password, role, postTitles
            )
            member.createAt = createAt
            member.deleteAt = deletedAt
            member.updateAt = updateAt
            member.orderNo = orderNo
            return member
        }

    }


    @Convert
    class PostTitlesConverter(
    ) : AttributeConverter<MutableList<String>, String> {

        /**
         * todo CopyRightHolder와는 완전 다른 아티스트 개별 소속사 - 이름이 중첩되는 게 있어서 헷갈릴 수 있다.
         * https://lingi04.tistory.com/37 참고
         */

        val delimiters = ","

        override fun convertToDatabaseColumn(attribute: MutableList<String>): String {

            val joiner = StringJoiner(delimiters)

            for (title in attribute) {
                joiner.add(title)
            }

            return joiner.toString()
        }

        override fun convertToEntityAttribute(dbData: String?): MutableList<String> {

            return dbData?.split(delimiters)?.toMutableList() ?: return mutableListOf()
        }
    }

}

