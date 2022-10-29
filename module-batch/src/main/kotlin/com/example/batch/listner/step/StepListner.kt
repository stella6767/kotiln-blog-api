package com.example.batch.listner.step

import mu.KotlinLogging
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.util.StopWatch
import java.util.*
import java.util.concurrent.TimeUnit


class StepListner(
) : StepExecutionListener {

    private val log = KotlinLogging.logger {  }

    //val watch = StopWatch()

    override fun beforeStep(stepExecution: StepExecution) {

        //watch.start("task")
        log.info { "beforeStep" }

    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus {

        val startTime = stepExecution.startTime
        log.debug { startTime }

        //endTime 은 StepExecutionListener 이후에 값이 저장되고 있습니다.
        //그래서 StepExecutionListener 의 afterStep 시점에서는 endTime 이 null 상태

        if (stepExecution.status == BatchStatus.COMPLETED) {
            val endTime = stepExecution.endTime
            log.debug { endTime }
            // get diff between end time and start time
            val diff = endTime?.time?.minus(startTime.time)
            // log diff time ??? 왜 null이지?
            log.debug { diff?.let { TimeUnit.SECONDS.convert(it, TimeUnit.MILLISECONDS) } }

            log.info { "chunkStep end" }
        }

        //  get job's end time


        return stepExecution.exitStatus
    }


}