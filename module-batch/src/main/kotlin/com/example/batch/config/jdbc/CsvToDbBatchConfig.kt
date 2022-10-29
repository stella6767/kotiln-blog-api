package com.example.batch.config.jdbc

import com.example.batch.dto.CsvMember
import com.example.batch.dto.CustomMember
import com.example.batch.listner.job.CommonJobListner
import com.example.batch.listner.step.StepListner
import com.example.batch.processor.CsvMemberToCustomMemberProcessor
import com.example.batch.processor.CustomMemberToCsvMemberProcessor
import com.example.batch.writer.CustomJdbcMemberWriter
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.ResourceLoader
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource


@Configuration
class CsvToDbBatchConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val dataSource: DataSource,
    private val resourceLoader: ResourceLoader,
) {

    private val log = KotlinLogging.logger { }
    private val chunkSize = 10
    private val jobName = "jdbcCsvToDbJob"
    val stepName = "jdbcCsvToDbStep"
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)


    @Bean
    fun jdbcCsvToDbJob(): Job {
        return jobBuilderFactory[jobName]
            .listener(CommonJobListner())
            .incrementer(RunIdIncrementer())
            .start(jdbcCsvToDbStep())
            //.next() //다음 스텝을 이어나갈 수 있음
            .build()
    }


    @Bean
    @JobScope
    fun jdbcCsvToDbStep(): Step {

        return stepBuilderFactory[stepName]
            .listener(StepListner())
            .chunk<CsvMember, CustomMember>(chunkSize)
            .reader(reader())
            .processor(CsvMemberToCustomMemberProcessor())
            //.writer(writer())
            .writer(CustomJdbcMemberWriter(jdbcTemplate))
            .build()
    }


    /**
     * Reader 정의
     */


    private fun reader(): FlatFileItemReader<CsvMember> {

        val strings =
            arrayOf("id", "email", "password", "role", "createAt", "updateAt", "deleteAt", "orderNo", "postTitles")

        return FlatFileItemReaderBuilder<CsvMember>()
            .name("customMemberCsvReader")
            .resource(ClassPathResource("/data/input/member.csv"))
            .linesToSkip(1)
            .delimited()
            .delimiter(DelimitedLineTokenizer.DELIMITER_COMMA)
            //.names(*strings)
            .names(
                "id", "email", "password", "role", "createAt", "updateAt", "deleteAt", "orderNo", "postTitles"
            )
            //.fieldSetMapper(BeanWrapperFieldSetMapper<CsvMember>())
//            .fieldSetMapper(object : BeanWrapperFieldSetMapper<CsvMember>() {
//                init {
//                    setTargetType(CsvMember::class.java)
//                }
//            })
            .fieldSetMapper { // 11
                CsvMember(
                    id = it.readString("id"),
                    email = it.readString("email"),
                    password = it.readString("password"),
                    role = it.readString("role"),
                    createAt = it.readString("createAt"),
                    updateAt = it.readString("updateAt"),
                    deleteAt = it.readString("deleteAt"),
                    orderNo = it.readLong("orderNo"),
                    postTitles = it.readString("postTitles"),
                )
            }
            .build()
    }


    fun writer(): JdbcBatchItemWriter<CustomMember> {

        return JdbcBatchItemWriterBuilder<CustomMember>()
//            .itemSqlParameterSourceProvider(
//                BeanPropertyItemSqlParameterSourceProvider<CustomMember>()
//            )
            .sql(
                """
                INSERT INTO 
                BatchMember 
                (email, password, role, postTitles, order_no, create_at, update_at, delete_at ) 
                VALUES 
                (:email, :password, :role, :postTitles, :orderNo, :createAt, :updateAt, :deleteAt )                                
            """.trimIndent()
            )
            .dataSource(dataSource)
            .beanMapped()
            .build()
    }

}