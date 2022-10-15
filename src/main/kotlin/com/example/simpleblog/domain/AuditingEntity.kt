package com.example.simpleblog.domain

import com.example.simpleblog.domain.post.PostRes
import com.example.simpleblog.util.dto.BaseDto
import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity(
    id:Long
) : AuditingEntityId(id) {



    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    var createAt: LocalDateTime = LocalDateTime.now()
        protected set



    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime = LocalDateTime.now()
        protected set


    protected fun setBaseDtoProperty(dto: BaseDto) {
        dto.id = this.id!!
        dto.createAt = this.createAt
        dto.updateAt = this.updateAt
    }

}



@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntityId(
    id: Long
) : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

}