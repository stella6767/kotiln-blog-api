package com.example.batch.config

import com.example.batch.dto.MemberWithPost
import com.example.batch.mapper.jdbc.MemberWithPostMapper
import com.example.batch.processor.MemberWithPostProcessor
import com.example.simpleblog.core.domain.member.Member
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcPagingItemReader
import org.springframework.batch.item.database.Order
import org.springframework.batch.item.database.PagingQueryProvider
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.BeanPropertyRowMapper
import javax.sql.DataSource


@Configuration
class JdbcBatchConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    //private val entityManagerFactory: EntityManagerFactory,
    private val dataSource: DataSource,
    private val resourceLoader: ResourceLoader,
) {

    private val log = KotlinLogging.logger {  }
    private val chunkSize = 10


    @Bean
    fun jdbcPagingItemReaderJob(): Job {
        return jobBuilderFactory["jdbcPagingItemReaderJob"]
            .incrementer(RunIdIncrementer())
            .start(jdbcPagingItemReaderStep())
            .build()
    }

    @Bean
    //@JobScope
    fun jdbcPagingItemReaderStep(): Step {
        return stepBuilderFactory["jdbcPagingItemReaderStep"]
            .chunk<Member, MemberWithPost>(chunkSize)
            .reader(jdbcPagingItemReader())
            .processor(MemberWithPostProcessor())
            .writer(jdbcPagingItemWriter())
            .build()
    }


    /**
     * Reader 정의
     */

    @Bean
    fun jdbcPagingItemReader(): JdbcPagingItemReader<Member> {

        val parameterValues: MutableMap<String, Any> = HashMap()
        //parameterValues["amount"] = 10
        return JdbcPagingItemReaderBuilder<Member>()
            .pageSize(chunkSize)
            .fetchSize(chunkSize)
            .dataSource(dataSource)
            .rowMapper(BeanPropertyRowMapper(Member::class.javaObjectType))
            //.rowMapper(MemberWithPostMapper())
            .queryProvider(createQueryProvider())
            //.parameterValues(parameterValues)
            .name("jdbcPagingItemReader")
            .build()
    }


    @Bean
    fun createQueryProvider(): PagingQueryProvider {
        val queryProvider = SqlPagingQueryProviderFactoryBean()
        queryProvider.setDataSource(dataSource) // Database에 맞는 PagingQueryProvider를 선택하기 위해
        //queryProvider.setSelectClause("m.id, m.email, m.password, m.create_at, m.update_at, m.email, m.password, m.role, GROUP_CONCAT(p.title) as postTitles")
        //queryProvider.setFromClause("from Member m inner join Post p on m.id = p.member_id group by m.id")

        //queryProvider.setSelectClause("m.id, m.email, m.password, m.create_at, m.update_at, m.email, m.password, m.role")
        queryProvider.setSelectClause("*")
        queryProvider.setFromClause("from Member m ")

        //queryProvider.setWhereClause("where amount >= :amount")
        val sortKeys: MutableMap<String, Order> = HashMap(1)
        sortKeys["id"] = Order.ASCENDING
        queryProvider.setSortKeys(sortKeys)

        return queryProvider.getObject()
    }


//

    private fun jdbcPagingItemWriter(): ItemWriter<MemberWithPost> {
        return ItemWriter<MemberWithPost> { list ->

            log.debug { "????=> $list" }

//            for (memberWithPost in list) {
//                log.debug ("Current MemberWithPost={}", memberWithPost)
//            }
        }
    }


}