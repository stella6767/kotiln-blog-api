package com.example.simpleblog.mvc.setup

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.utility.DockerImageName
import javax.sql.DataSource


@TestConfiguration
open class TestContainerSetup(

) {

    private val log = KotlinLogging.logger {  }

    //https://www.baeldung.com/spring-dynamicpropertysource
    //withInitScript()는 클래스패스에서 실행할 쿼리를 찾는다. 예제 코드는 src/test/resources 폴더에 위치한 init.sql 파일을 실행한다.



    @Bean
    //@DependsOn("mySqlTestContainer")
    @Primary
    fun dataSource(): DataSource {
        val source = DataSourceBuilder.create()
            //MariaDBContainer.MARIADB_PORT
            .url(mariadb.jdbcUrl)
            .driverClassName("org.mariadb.jdbc.Driver")
            .username(mariadb.username)
            .password(mariadb.password)
            .build()


        return source
    }

    @Test
    fun test(){

        log.debug { mariadb.databaseName }

        //val resource: URL = ScriptUtils::class.java.classLoader.getResource(initScriptPath)
        //log.debug { resource }
    }

    companion object {


        private val MARIADB_IMAGE = DockerImageName.parse("mariadb:10.5.5")

        val initScriptPath = "module-mvc/src/test/resources/static/sql/testinit.sql"

        val mariadb: MariaDBContainer<*> = MariaDBContainer(MARIADB_IMAGE)
            .withCommand("--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci")
            //.withInitScript(initScriptPath)
//

        init {
            mariadb.start()
        }



        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { mariadb.jdbcUrl }
            registry.add("spring.datasource.username") { mariadb.username }
            registry.add("spring.datasource.password") { mariadb.password }
        }


        /**
         * 밑에처럼 직접 정의한 변수를 registry에 넣을 수도 있지만, 나는 기본 값으로
         */

//        const val DATABASE_NAME: String = "blog_test"
//        const val USERNAME: String = "root"
//        const val PASSWORD: String = "password"

    }



}