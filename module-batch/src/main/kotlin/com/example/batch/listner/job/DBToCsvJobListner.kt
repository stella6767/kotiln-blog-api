package com.example.batch.listner.job

import mu.KotlinLogging
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import java.util.concurrent.TimeUnit

class DBToCsvJobListner(

) : JobExecutionListener {

    private val log = KotlinLogging.logger {  }

    override fun beforeJob(jobExecution: JobExecution) {


        log.debug { "beforeJob" }
    }

    override fun afterJob(jobExecution: JobExecution) {

        val start = jobExecution.createTime
        val end = jobExecution.endTime
        val diff = end?.time?.minus(start?.time)

        log.debug {
            """
                
                Job 실행시간 측정!!               
                ${diff?.let { TimeUnit.SECONDS.convert(it, TimeUnit.MILLISECONDS) }}
                              
            """.trimIndent()

        }
    }

}