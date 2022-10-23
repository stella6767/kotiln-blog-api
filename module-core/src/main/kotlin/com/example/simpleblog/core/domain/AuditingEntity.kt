package com.example.simpleblog.core.domain

import com.example.simpleblog.core.config.jpa.OrderNoInitListner
import com.example.simpleblog.core.util.dto.BaseResponseDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*


@EntityListeners(value = [AuditingEntityListener::class, OrderNoInitListner::class])
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

    @Column(name = "delete_at")
    var deleteAt: LocalDateTime? = null
        protected set

    @Column(name = "order_no")
    var orderNo: Long? = null
        protected set


    protected fun setBaseDtoProperty(dto: BaseResponseDto) {
        dto.id = this.id
        dto.createAt = this.createAt
        dto.updateAt = this.updateAt
        dto.isShow = this.deleteAt != null

    }

}



@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntityId(
    id: Long
) : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = id
        protected set

}