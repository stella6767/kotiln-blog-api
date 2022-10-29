package com.example.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication


@EnableBatchProcessing
@EntityScan(basePackages = ["com.example.simpleblog.core"])
@SpringBootApplication
class BatchApplication


fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}