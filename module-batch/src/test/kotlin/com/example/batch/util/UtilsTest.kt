package com.example.batch.util

import com.example.batch.mapper.csv.CustomMemberCsvAggregator
import org.junit.jupiter.api.Test

class UtilsTest {



    @Test
    fun test(){

        val joinToString = CustomMemberCsvAggregator().headerNames.joinToString()

        println(joinToString)
    }

}