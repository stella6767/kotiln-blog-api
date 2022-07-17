package com.example.simpleblog.domain.commenet

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct
import javax.persistence.EntityManager

interface CommentRepository  {
}

@Repository
class CommentCustomRepository(
        private val entityManager: EntityManager
) {

    val log = KotlinLogging.logger{}

    @PostConstruct
    fun test(){
        log.info { "$entityManager 는 주입이 될랑말랑" }
    }



}