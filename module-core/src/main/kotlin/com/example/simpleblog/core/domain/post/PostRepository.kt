package com.example.simpleblog.core.domain.post

import com.example.simpleblog.core.util.dto.SearchCondition
import com.example.simpleblog.core.util.func.dynamicQuery
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.querydsl.from.fetch
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import com.linecorp.kotlinjdsl.spring.data.updateQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils
import javax.persistence.criteria.JoinType

interface PostRepository : JpaRepository<Post, Long>, PostCustomRepository {

}

interface PostCustomRepository{

    fun findPosts(pageable: Pageable, searchCondition: SearchCondition): Page<Post>
    fun updateOrderNo(orderNo: Long)
}

class PostCustomRepositoryImpl(
    private val queryFactory: SpringDataQueryFactory
): PostCustomRepository {

    override fun findPosts(pageable: Pageable, searchCondition: SearchCondition): Page<Post> {

        val results = queryFactory.listQuery<Post> {
            select(entity(Post::class))
            from(entity(Post::class))
            where(
                dynamicQuery(searchCondition)
            )
            fetch(Post::member, joinType = JoinType.LEFT)
            limit(pageable.pageSize)
            offset(pageable.offset.toInt())
            orderBy(ExpressionOrderSpec(column(Post::id), false))
        }

        val countQuery = queryFactory.listQuery<Post> {
            select(entity(Post::class))
            from(entity(Post::class))
            where(
                dynamicQuery(searchCondition)
            )
        }

        return PageableExecutionUtils.getPage(results, pageable){
            countQuery.size.toLong()
        }

    }


    override fun updateOrderNo(orderNo: Long){

        val updateQuery = queryFactory.updateQuery<Post> {
            where(col(Post::id).equal(orderNo))
            setParams(col(Post::orderNo) to orderNo)
        }

        updateQuery.executeUpdate()
    }



}