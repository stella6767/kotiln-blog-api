package com.example.batch

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import java.nio.file.Files
import java.nio.file.Path

@SpringBootTest
class BatchApplicationTest {


    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    @Test
    fun contextLoadss(){}


    @Test
    fun resourceLoaderTest(){

        val resource: Resource = resourceLoader.getResource("classpath:data/output/member.csv")
        println(Files.readString(Path.of(resource.uri)));
    }


}