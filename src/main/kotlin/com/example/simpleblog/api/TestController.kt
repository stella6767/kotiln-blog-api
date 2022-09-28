package com.example.simpleblog.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class TestController(

) {

    @GetMapping("/health")
    fun healthTest():String = "hello kotiln-blog"



//    @GetMapping("/error") //시큐리티 Default error redirest 주소같애여.
//    fun errorTest(): String {
//        return "error"
//    }


}