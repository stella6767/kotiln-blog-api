package com.example.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@ComponentScan(
    "com.example.common",
    "com.example.batch"
)
class Test

fun main(args: Array<String>) {
    runApplication<Test>(*args)
}
