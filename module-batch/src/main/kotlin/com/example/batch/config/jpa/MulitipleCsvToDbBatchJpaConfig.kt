package com.example.batch.config.jpa

import com.example.batch.dto.CsvMember
import com.example.batch.dto.CustomMember
import com.example.simpleblog.core.domain.member.BatchMember
import com.example.simpleblog.core.domain.member.Member
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.MultiResourceItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import java.time.LocalDateTime
import javax.persistence.EntityManagerFactory


@Configuration
class MulitipleCsvToDbBatchJpaConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val entityManagerFactory: EntityManagerFactory,
    private val resourceLoader: ResourceLoader
) {


    companion object {
        const val JOB_NAME = "CsvToDbBatchJpaJob"
        const val STEP_NAME = "CsvToDbBatchJpaStep"
    }

    private val chunkSize = 10



    fun csvToJpaFileReader(): MultiResourceItemReader<BatchMember> {

        val multiResourceItemReader = MultiResourceItemReader<BatchMember>()
        multiResourceItemReader.setResources(
            ResourcePatternUtils.getResourcePatternResolver(this.resourceLoader).getResources(
                "classpath:data/input/*.csv"
            )
        )
        multiResourceItemReader.setDelegate(reader())
        return multiResourceItemReader
    }


    private fun reader(): FlatFileItemReader<BatchMember> {

        val strings =
            arrayOf("id", "email", "password", "role", "createAt", "updateAt", "deleteAt", "orderNo", "postTitles")

        return FlatFileItemReaderBuilder<BatchMember>()
            .name("customMemberCsvReader")
            .lineMapper { line, lineNumber ->

                val list = line.split(",")


                BatchMember.of(
                    list[0].toLong(),
                    LocalDateTime.parse(list[1]),
                    LocalDateTime.parse(list[2]),
                    LocalDateTime.parse(list[3]),
                    list[4].toLong(),
                    list[5],
                    list[6],
                    Member.Role.valueOf(list[7]),
                    list[8].split(",").toMutableList(),

                )

            }
            .build()
    }



}