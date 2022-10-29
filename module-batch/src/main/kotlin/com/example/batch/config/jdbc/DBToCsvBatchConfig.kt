package com.example.batch.config.jdbc

import com.example.batch.dto.CustomMember
import com.example.batch.listner.job.DBToCsvJobListner
import com.example.batch.listner.step.StepListner
import com.example.batch.mapper.csv.CustomMemberCsvMapper
import com.example.batch.processor.CustomMemberProcessor
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcPagingItemReader
import org.springframework.batch.item.database.Order
import org.springframework.batch.item.database.PagingQueryProvider
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.DataClassRowMapper
import java.nio.charset.StandardCharsets
import javax.sql.DataSource

/**
 * @JobScope는 Step 선언문에서 사용 가능하고, @StepScope는 Tasklet이나 ItemReader, ItemWriter, ItemProcessor에서 사용할 수 있습니다.
 *
 * https://cheese10yun.github.io/spring-batch-basic/
 */


@Configuration
class DBToCsvBatchConfig(
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
            .listener(DBToCsvJobListner())
            .incrementer(RunIdIncrementer())
            .start(jdbcPagingItemReaderStep())
            .build()
    }

    @Bean
    @JobScope
    fun jdbcPagingItemReaderStep(): Step {
        return stepBuilderFactory["jdbcPagingItemReaderStep"]
            .listener(StepListner())
            .chunk<CustomMember, CustomMember>(chunkSize)
            .reader(jdbcPagingItemReader())
            .processor(CustomMemberProcessor())
            .writer(flatFileMemberItemWriter())
            .build()
    }


    /**
     * Reader 정의
     */

    // https://stackoverflow.com/questions/62864662/spring-jdbc-beanpropertyrowmapper-with-kotlin

    @Bean
    fun jdbcPagingItemReader(): JdbcPagingItemReader<CustomMember> {

        // DataClassRowMapper 를 사용!

        val parameterValues: MutableMap<String, Any> = HashMap()
        //parameterValues["amount"] = 10
        return JdbcPagingItemReaderBuilder<CustomMember>()
            .pageSize(chunkSize)
            .fetchSize(chunkSize)
            .dataSource(dataSource)
            //.rowMapper(BeanPropertyRowMapper(CsvMember::class.java))
            .rowMapper(DataClassRowMapper(CustomMember::class.java))
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
            m.order_no as orderNo,
            GROUP_CONCAT(p.title) as postTitles
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

    private fun jdbcPagingItemWriter(): ItemWriter<CustomMember> {
        return ItemWriter<CustomMember> { list ->

            for (csvMember in list) {

                log.debug { "csvMember=>  $csvMember "}
            }

        }
    }



    private fun flatFileMemberItemWriter(): FlatFileItemWriter<CustomMember> {

        return FlatFileItemWriterBuilder<CustomMember>()
            .name("customMemberFileWriter")
            .resource(FileSystemResource("module-batch/src/main/resources/data/output/member.csv"))
            .append(true)
            .lineAggregator(CustomMemberCsvMapper().delimitedLineAggregator())
            .headerCallback {
                it.write(CustomMemberCsvMapper().headerNames.joinToString { "," })
            }
            .encoding(StandardCharsets.UTF_8.name())
            .build()

    }


}