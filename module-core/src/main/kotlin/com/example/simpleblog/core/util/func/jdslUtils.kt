package com.example.simpleblog.core.util.func

import com.example.simpleblog.core.domain.member.Member
import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.util.dto.SearchCondition
import com.example.simpleblog.core.util.dto.SearchCondition.SearchType
import com.linecorp.kotlinjdsl.query.spec.predicate.PredicateSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.querydsl.SpringDataCriteriaQueryDsl
import org.slf4j.LoggerFactory


private val log = LoggerFactory.getLogger(object {}::class.java.`package`.name)

fun <T> SpringDataCriteriaQueryDsl<T>.dynamicQuery(
    condition: SearchCondition?
): PredicateSpec {

    val keyword = condition?.keyword
    log.debug("keyword==>$keyword")
    return when(condition?.searchType) {
        SearchType.CONTENT -> and(keyword?.let { column(Post::content).like("%$keyword%") })
        SearchType.EMAIL -> and(keyword?.let { column(Member::email).like("%$keyword%") })
        SearchType.TITLE -> and(keyword?.let { column(Post::title).like("%$keyword%") })
        else -> {PredicateSpec.empty}
    }
}
