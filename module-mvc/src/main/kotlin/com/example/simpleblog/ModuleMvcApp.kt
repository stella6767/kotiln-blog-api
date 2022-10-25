package com.example.simpleblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@ConfigurationPropertiesScan
@EnableJpaRepositories(basePackages = ["com.example.simpleblog.core"])
@SpringBootApplication
class ModuleMvcApp

fun main(args: Array<String>) {

    runApplication<ModuleMvcApp>(*args)
}
