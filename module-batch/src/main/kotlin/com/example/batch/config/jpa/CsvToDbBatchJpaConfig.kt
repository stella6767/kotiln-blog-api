package com.example.batch.config.jpa

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory


@Configuration
class CsvToDbBatchJpaConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val entityManagerFactory: EntityManagerFactory,
) {


    companion object {
        const val JOB_NAME = "CsvToDbBatchJpaJob"
        const val STEP_NAME = "CsvToDbBatchJpaStep"
    }

    private val chunkSize = 10





}