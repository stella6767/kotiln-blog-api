package com.example.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * https://backtony.github.io/spring/2022-06-02-spring-module-1/
 *
 * 루트프로젝트 하위에 바로 위치시키므로 패키지 스캔 어노테이션을 생략
 */




//@ComponentScan(basePackages = ["com.example"])

@EnableJpaRepositories(basePackages = ["com.example.common"])
@EntityScan(basePackages = ["com.example.common"])
@SpringBootApplication(scanBasePackages = ["com.example.web","com.example.common"])
//@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
