package com.example.batch.job

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory : StepBuilderFactory,
) {

    private val log = KotlinLogging.logger {  }

    @Bean
    fun simpleJob(): Job {
        return jobBuilderFactory!!["simpleJob"]
            .start(simpleStep1())
            .build()
    }

    @Bean
    fun simpleStep1(): Step {
        return stepBuilderFactory!!["simpleStep1"]
            .tasklet { contribution: StepContribution?, chunkContext: ChunkContext? ->
                log.info(">>>>> This is Step1")
                RepeatStatus.FINISHED
            }
            .build()
    }

}

