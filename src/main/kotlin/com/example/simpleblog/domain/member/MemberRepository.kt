package com.example.simpleblog.domain.member

import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.query.spec.OrderSpec
import com.linecorp.kotlinjdsl.query.spec.expression.ColumnSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.querydsl.orderby.OrderByDsl
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {

}

interface MemberCustomRepository {
    fun findAllByPage(pageable: Pageable): Page<Member>
}


class MemberCustomRepositoryImpl(
        private val queryFactory: SpringDataQueryFactory,
) : MemberCustomRepository {

    override fun findAllByPage(pageable: Pageable): Page<Member> {


        val members = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
            limit(pageable.pageSize)
            offset(pageable.offset.toInt())
            orderBy(ExpressionOrderSpec(expression = column(Member::id),
                    ascending = false))
        }

//        queryFactory.listQuery<String> {
//            select(column(Member::email))
//            from(entity(Member::class))
//        }

        val countQuery = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
        }


//        return PageImpl( members.sortedByDescending { it.id}
//                , pageable, countQuery.size.toLong())
        return PageImpl(members, pageable, countQuery.size.toLong())
    }

}

