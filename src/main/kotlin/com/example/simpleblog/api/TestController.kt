package com.example.simpleblog.api

import com.example.simpleblog.util.dto.SeachCondition
import com.example.simpleblog.util.dto.SearchType
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(

) {

    private val log = KotlinLogging.logger {  }

    @GetMapping("/health")
    fun healthTest():String = "hello kotiln-blog"

    @GetMapping("/enum2")
    fun enumTest2(searchType: SearchType): String {

        return searchType.name
    }


    @GetMapping("/enum")
    fun enumTest(searchcCondition: SeachCondition): String {


        log.info { """
            $searchcCondition
           
            ${searchcCondition.searchType}                    
            ${searchcCondition.keyword}                    
            
            
        """.trimIndent() }

        return "test"
    }


//    @GetMapping("/error") //시큐리티 Default error redirest 주소같애여.
//    fun errorTest(): String {
//        return "error"
//    }


}