package com.example.simpleblog.mvc.web

import com.example.simpleblog.core.util.dto.SearchCondition
import com.example.simpleblog.core.util.dto.SearchCondition.SearchType
import com.example.simpleblog.mvc.service.post.PostQueryService
import com.example.simpleblog.mvc.service.post.PostService
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(
    private val postQueryService: PostQueryService,
) {

    private val log = KotlinLogging.logger {  }

    @GetMapping("/autocomplete")
    fun autoCompleteTest(@RequestParam word:String): MutableList<String> {

        return postQueryService.autoCompletePostTitle(word)
    }


    @GetMapping("/health")
    fun healthTest():String = "hello kotiln-blog"

    @GetMapping("/enum2")
    fun enumTest2(searchType: SearchType): String {

        return searchType.name
    }


    @GetMapping("/enum")
    fun enumTest(searchcCondition: SearchCondition): String {


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