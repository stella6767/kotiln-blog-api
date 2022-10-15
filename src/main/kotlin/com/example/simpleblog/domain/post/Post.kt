package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import javax.persistence.*


@Entity
@Table(name = "Post")
class Post(
    id:Long = 0,
        title:String,
        content:String,
        member: Member
) : AuditingEntity(id) {

   @Column(name = "title", nullable = false)
   var title:String = title
      protected set

   @Column(name = "content", length = 1000)
   var content:String = content
      protected set

   @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
   var member:Member = member
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
        return "Post(id=$id, title='$title', content='$content', member=$member)"
    }

    companion object{

    }

}



