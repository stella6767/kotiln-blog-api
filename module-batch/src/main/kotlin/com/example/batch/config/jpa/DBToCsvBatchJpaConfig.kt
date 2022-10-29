package com.example.batch.config.jpa

import com.example.batch.listner.job.CommonJobListner
import com.example.batch.mapper.csv.CustomMemberCsvAggregator
import com.example.simpleblog.core.domain.member.Member
import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.batch.item.file.FlatFileItemWriter
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor
import org.springframework.batch.item.file.transform.DelimitedLineAggregator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResource
import java.nio.charset.StandardCharsets
import javax.persistence.EntityManagerFactory

// https://github.com/cheese10yun/blog-sample/blob/master/batch-study/docs/batch-item-reader.md

/**
 * 다른 모듈의 엔티티를 불러와서 hql를 실행하려고 하면 @EntityScan으로 해당 엔티티를 스캔해야된다.
 */


@Configuration
class DBToCsvBatchJpaConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val entityManagerFactory: EntityManagerFactory,
) {

    companion object {
        const val JOB_NAME = "DBToCsvBatchJob"
        const val STEP_NAME = "DBToCsvBatchStep"
    }

    private val log = KotlinLogging.logger { }
    private val chunkSize = 10


    @Bean
    fun dormantMemberCleanJob(): Job = jobBuilderFactory.get(JOB_NAME)
        .listener(CommonJobListner())
        .incrementer(RunIdIncrementer())
        .start(dbToCsvBatchJpaStep())
        .build()

    @Bean
    @JobScope
    fun dbToCsvBatchJpaStep(

    ): Step = stepBuilderFactory.get(STEP_NAME)
        .chunk<Member, Member>(chunkSize)
        .reader(jpaMemberReader())
        .processor(memberProcessor())
        .writer(flatFileMemberItemWriter())
        .build()


    //    @Bean
//    @StepScope
    fun jpaMemberReader(): JpaPagingItemReader<out Member> =
        JpaPagingItemReaderBuilder<Member>()
            .name(DBToCsvBatchJpaConfig::jpaMemberReader.name)
            .entityManagerFactory(entityManagerFactory)
//            .queryString(
//                """
//                SELECT m
//                FROM
//                Member m
//                ORDER BY ${Member::id.name} asc
//            """.trimIndent()
//            )
            .queryString("select m from Member m order by id asc")
//        .parameterValues(
//            mapOf(
//                "status" to MemberStatus.DORMANT,
//                "dormantDate" to LocalDate.parse(
//                    dormantDate,
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
//                ).atStartOfDay()
//            )
//        )
            .pageSize(chunkSize)
            //.maxItemCount(chunkSize)
            .build()

    fun memberProcessor() = ItemProcessor<Member, Member> {
        it
    }


    private fun flatFileMemberItemWriter(): FlatFileItemWriter<Member> {
        val names = arrayOf(
            "id",
            "email",
            "password",
            "role",
            "createAt",
            "updateAt",
            "deleteAt",
            "orderNo"
        )

        return FlatFileItemWriterBuilder<Member>()
            .name(DBToCsvBatchJpaConfig::flatFileMemberItemWriter.name)
            .resource(FileSystemResource("module-batch/src/main/resources/data/output/member2.csv"))
            .append(true)
            .lineAggregator(object : DelimitedLineAggregator<Member>() {
                init {
                    setDelimiter(",")
                    setFieldExtractor(object : BeanWrapperFieldExtractor<Member>() {
                        init {

                            setNames(
                                names
                            )
                        }
                    })
                }
            })
            .headerCallback {
                it.write(names.joinToString())
            }
            .encoding(StandardCharsets.UTF_8.name())
            .build()

    }


}