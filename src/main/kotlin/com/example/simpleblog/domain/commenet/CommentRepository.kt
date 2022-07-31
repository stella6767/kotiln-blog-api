package com.example.simpleblog.domain.commenet

import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import javax.persistence.EntityManager

interface CommentRepository {
    fun saveComment(comment: Comment): Comment
}


@Repository
class CommentRepositoryImpl(
    private val queryFactory: SpringDataQueryFactory,
    private val em: EntityManager
) : CommentRepository {


    override fun saveComment(comment: Comment): Comment {
        Assert.notNull(comment, "Entity must not be null")
        return if (comment.id == 0L) {
            em.persist(comment)
            comment
        } else {
            em.merge(comment)
        }
    }


}
