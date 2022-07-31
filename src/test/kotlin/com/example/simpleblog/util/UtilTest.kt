package com.example.simpleblog.util

import mu.KotlinLogging
import org.junit.jupiter.api.Test

class UtilTest {

    private val log = KotlinLogging.logger {  }


    @Test
    fun errorLogTest(){

        //throw RuntimeException("error")
        log.error { "error!!" }

    }

}