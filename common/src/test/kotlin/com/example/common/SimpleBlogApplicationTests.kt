package com.example.common

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SimpleBlogApplicationTests(
    @Autowired
    val df:DefaultListableBeanFactory
) {

    @Test
    fun contextLoads() {
    }


    @Test
    fun printBeanNames(){

        val names = df.beanDefinitionNames

        for (name in names) {
            val bean = df.getBean(name)
            println("name = $name  object = $bean ")
        }

    }


}
