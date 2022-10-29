package com.example.batch.config

import com.example.batch.dto.CsvMember
import com.example.batch.dto.MemberWithPost
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
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
import org.springframework.jdbc.core.DataClassRowMapper
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
            .chunk<CsvMember, CsvMember>(chunkSize)
            .reader(jdbcPagingItemReader())
            //.processor(MemberWithPostProcessor())
            .writer(jdbcPagingItemWriter())
            .build()
    }


    /**
     * Reader 정의
     */

    // https://stackoverflow.com/questions/62864662/spring-jdbc-beanpropertyrowmapper-with-kotlin

    @Bean
    fun jdbcPagingItemReader(): JdbcPagingItemReader<CsvMember> {

        // DataClassRowMapper 를 사용!

        val parameterValues: MutableMap<String, Any> = HashMap()
        //parameterValues["amount"] = 10
        return JdbcPagingItemReaderBuilder<CsvMember>()
            .pageSize(chunkSize)
            .fetchSize(chunkSize)
            .dataSource(dataSource)
            //.rowMapper(BeanPropertyRowMapper(CsvMember::class.java))
            .rowMapper(DataClassRowMapper(CsvMember::class.java))
//            .rowMapper(RowMapper { rs, rowNum ->
//
//                val id = rs.getInt("id")
//
//                log.debug { "?????=>$id" }
//
//                Member.createFakeMember(1)
//            })
            .queryProvider(createQueryProvider())
            //.parameterValues(parameterValues)
            .name("jdbcPagingItemReader")
            .build()
    }


    /**
     * DB의 컬럼명과 bean 객체의 속성명이 일치하다면 BeanPropertyRowMapper 를 이용하여 자동으로 객체변환을 할 수 있다.
     * 하지만 그럴 경우가 희박할 뿐더러.. kotlin에서는 일치한다 하더라도 무슨 이유에서진 잘 변환이 안 된다.
     *
     */


    @Bean
    fun createQueryProvider(): PagingQueryProvider {
        val queryProvider = SqlPagingQueryProviderFactoryBean()
        queryProvider.setDataSource(dataSource) // Database에 맞는 PagingQueryProvider를 선택하기 위해
        //queryProvider.setSelectClause("m.id, m.email, m.password, m.create_at, m.update_at, m.email, m.password, m.role, GROUP_CONCAT(p.title) as postTitles")
        queryProvider.setSelectClause("""
            m.id as id, 
            m.email as email, 
            m.password as password,  
            m.create_at as createAt, 
            m.delete_at as deleteAt, 
            m.update_at as updateAt,  
            m.role as role,  
            m.order_no as orderNo
        """.trimIndent())
        queryProvider.setFromClause("""           
            from Member m 
            left outer join 
            Post p 
            on m.id = p.member_id                
        """.trimIndent())

        //queryProvider.setSelectClause("m.id, m.email, m.password, m.create_at, m.update_at, m.email, m.password, m.role")
        //queryProvider.setFromClause("from Member m ")
        //queryProvider.setWhereClause("where amount >= :amount")

        val sortKeys: MutableMap<String, Order> = HashMap(1)
        sortKeys["id"] = Order.ASCENDING
        queryProvider.setSortKeys(sortKeys)
        queryProvider.setGroupClause("group by m.id")

        return queryProvider.getObject()
    }


//

    private fun jdbcPagingItemWriter(): ItemWriter<CsvMember> {
        return ItemWriter<CsvMember> { list ->

            for (csvMember in list) {

                log.debug { "csvMember=>  $csvMember "}
            }

        }
    }


}