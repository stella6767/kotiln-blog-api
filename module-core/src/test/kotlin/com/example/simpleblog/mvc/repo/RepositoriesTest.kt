package com.example.simpleblog.mvc.repo

import com.example.simpleblog.core.config.P6spyPrettySqlFormatter
import com.example.simpleblog.core.domain.commenet.CommentRepository
import com.example.simpleblog.core.domain.commenet.CommentRepositoryImpl
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.core.util.dto.SearchCondition
import com.example.simpleblog.core.util.dto.SearchCondition.SearchType
import com.linecorp.kotlinjdsl.query.creator.CriteriaQueryCreatorImpl
import com.linecorp.kotlinjdsl.query.creator.SubqueryCreatorImpl
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactoryImpl
import com.p6spy.engine.spy.P6SpyOptions
import mu.KotlinLogging
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@DataJpaTest
class RepositoriesTest {

    private val log = KotlinLogging.logger { }

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var commentRepository: CommentRepository

    @Test
    fun setupTest() {
        log.info { "setUp!!" }
    }

    @Test
    fun findCommentByAncestorCommentTest(){

        val byAncestorComment = commentRepository.findCommentByAncestorComment(3)

        for (comment in byAncestorComment) {
            log.info { comment }
        }

    }



    @Test
    fun jdslDynamicQueryTest() {
        val (pageable, condition) = pageRequestSearchConditionPair()

        val posts = postRepository.findPosts(pageable = pageable, searchCondition = condition).content

        for (post in posts) {
            log.info { "post==>$post" }
        }

        Assertions.assertThat(posts.size).isEqualTo(6)
    }

    private fun pageRequestSearchConditionPair(): Pair<PageRequest, SearchCondition> {
        val pageable = PageRequest.of(0, 100)
        val condition = SearchCondition(
            searchType = SearchType.TITLE,
            keyword = "Kot"
        )
        return Pair(pageable, condition)
    }


    @TestConfiguration
    class Testconfiguation(
        @Suppress("SpringJavaInjectionPointsAutowiringInspection") @Autowired
        private val em: EntityManager
    ) {


        @Bean
        fun commentRepository() : CommentRepository {

            return CommentRepositoryImpl(springDataQueryFactory(), em)
        }

        @Bean
        fun springDataQueryFactory(): SpringDataQueryFactory {
            P6SpyOptions.getActiveInstance().logMessageFormat = P6spyPrettySqlFormatter::class.java.name
            return SpringDataQueryFactoryImpl(
                criteriaQueryCreator = CriteriaQueryCreatorImpl(
                    em
                ),
                SubqueryCreatorImpl()
            )

        }

    }

}