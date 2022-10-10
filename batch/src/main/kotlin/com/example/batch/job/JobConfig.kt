package com.example.batch.job

import mu.KotlinLogging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
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
            .start(simpleStep1(null))
            .next(simpleStep2(null))
            .build()
    }

    @Bean
    @JobScope
    fun simpleStep1(@Value("#{jobParameters[requestDate]}") requestDate:String?): Step {
        return stepBuilderFactory!!["simpleStep1"]
            .tasklet { contribution: StepContribution, chunkContext: ChunkContext ->
                log.info(">>>>> This is Step1")
                log.info(">>>>> requeestDate==>$requestDate")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @Bean
    @JobScope
    fun simpleStep2(@Value("#{jobParameters[requestDate]}") requestDate: String?): Step {
        return stepBuilderFactory["simpleStep2"]
            .tasklet { contribution: StepContribution, chunkContext: ChunkContext ->
                log.info(">>>>> This is Step2")
                log.info(">>>>> requestDate = {}", requestDate)
                RepeatStatus.FINISHED
            }
            .build()
    }

}

