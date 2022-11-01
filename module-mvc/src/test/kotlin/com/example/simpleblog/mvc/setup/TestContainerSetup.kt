package com.example.simpleblog.mvc.setup

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.testcontainers.containers.MariaDBContainer

import org.testcontainers.utility.DockerImageName
import javax.annotation.PreDestroy

@Component
open class TestContainerSetup(

) {


    @PreDestroy
    fun stop(){
        MY_SQL_CONTAINER.stop()
    }


    companion object {

        @JvmStatic
        val MY_SQL_CONTAINER: MariaDBContainer<*> =
            // image for linux/arm64/v8 m1 support
            DockerImageName.parse("mariadb")
                //.asCompatibleSubstituteFor("mysql")
                .let { compatibleImageName -> MariaDBContainer<Nothing>(compatibleImageName) }
                .apply {
                    withDatabaseName(DATABASE_NAME)
                    withUsername(USERNAME)
                    withPassword(PASSWORD)
                    withEnv("MYSQL_USER", USERNAME)
                    withEnv("MYSQL_PASSWORD", PASSWORD)
                    withEnv("MYSQL_ROOT_PASSWORD", PASSWORD)
                    //withInitScript("src/test/resources/initData/202211011626.sql");
                    start()
                }



        const val DATABASE_NAME: String = "blog_test"
        const val USERNAME: String = "root"
        const val PASSWORD: String = "password"

    }


    private val log = KotlinLogging.logger {  }


}