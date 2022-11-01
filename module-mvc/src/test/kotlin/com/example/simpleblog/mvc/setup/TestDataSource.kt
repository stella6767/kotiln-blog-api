package com.example.simpleblog.mvc.setup

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.testcontainers.containers.MariaDBContainer
import javax.sql.DataSource

@TestConfiguration
class TestDataSource(

) : TestContainerSetup() {

    @Bean
    //@DependsOn("mySqlTestContainer")
    @Primary
    fun dataSource(): DataSource =
        DataSourceBuilder.create()
                //MariaDBContainer.MARIADB_PORT
            .url("jdbc:mariadb://localhost:" +
                    "${MY_SQL_CONTAINER.getMappedPort(3306)}/" +
                    DATABASE_NAME)
            .driverClassName("org.mariadb.jdbc.Driver")
            .username(USERNAME)
            .password(PASSWORD)
            .build()


}