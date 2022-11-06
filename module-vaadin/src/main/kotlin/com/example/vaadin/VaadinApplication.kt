package com.example.vaadin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@ConfigurationPropertiesScan
@EnableJpaRepositories(basePackages = ["com.example.simpleblog.core"])
@SpringBootApplication
class VaadinApplication



fun main(args: Array<String>) {

    runApplication<VaadinApplication>(*args)
}
