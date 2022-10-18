package com.example.simpleblog.domain.post

import com.example.simpleblog.config.jpa.converts.PostTypeConverter
import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "Post")
class Post(
    id: Long = 0,
    title: String ,
    content: String ,
    reservateAt: LocalDateTime?,
    postType: PostType,
    member: Member
) : AuditingEntity(id) {

    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    @Column(name = "content", length = 1000)
    var content: String = content
        protected set

    @Convert(converter = PostTypeConverter::class)
    @Column(name = "post_type")
    var postType: PostType = postType
        protected set

    @Column(name = "resevate_at")
    var reservateAt: LocalDateTime? = reservateAt
        protected set


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        protected set


    fun toDto(): PostRes {

        val dto = PostRes(
            title = this.title,
            content = this.content,
            member = this.member.toDto()
        )

        setBaseDtoProperty(dto)

        return dto
    }


    override fun toString(): String {
        return "Post(id=$id, title='$title', content='$content', member=$member, createAt=$createAt, updatedAt=$updateAt)"
    }

    companion object {

    }


}


enum class PostType (
    val info:String
) {

    GOSSIP("잡담"), TECH("기술");

    @JsonCreator
    fun from(s:String): PostType {
        return PostType.valueOf(s.uppercase(Locale.KOREA))
    }

    companion object {

        fun ofCode(dbData:String?): PostType {
            return Arrays.stream(PostType.values()).filter{
                it.name == dbData
            }.findAny().orElse(GOSSIP)

        }

    }
}
