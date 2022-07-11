package com.example.simpleblog.domain.member

import com.linecorp.kotlinjdsl.query.spec.OrderSpec
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
            //orderBy()

            limit(pageable.pageSize)
            offset(pageable.offset.toInt())

        }

        val countQuery = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
        }

        return PageImpl(members, pageable, countQuery.size.toLong())
    }

}

