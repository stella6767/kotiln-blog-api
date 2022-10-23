package com.example.simpleblog.core.config.jpa


import com.example.simpleblog.core.config.CoreBeanAccesseor
import com.example.simpleblog.core.domain.AuditingEntity
import com.example.simpleblog.core.domain.JDBCTemplateRepository
import com.example.simpleblog.core.domain.post.PostRepository
import mu.KotlinLogging
import javax.persistence.PostPersist


class OrderNoInitListner {

    private val log = KotlinLogging.logger {  }

    @PostPersist
    fun postInsert(entity: AuditingEntity) {
        log.info { "after persist=>${entity.id}" }
        CoreBeanAccesseor.getBean(JDBCTemplateRepository::class).updatePostOrderNo(entity.id)
    }


}
