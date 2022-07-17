package com.example.simpleblog.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository
import java.io.Serializable


interface CommonJpaRepository<T : Serializable?, ID : Serializable?> {
    fun <S : T?> save(entity: S): S

}


//@NoRepositoryBean
interface Dao<T : AuditingEntity?> : CommonJpaRepository<T, Long?>


@Repository
class GenericDao : Dao<AuditingEntity?> {
    override fun <S : AuditingEntity?> save(entity: S): S {
        TODO("Not yet implemented")
    }
}


