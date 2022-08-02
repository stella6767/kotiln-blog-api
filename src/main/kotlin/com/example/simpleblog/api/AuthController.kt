package com.example.simpleblog.api

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession


@RequestMapping("/auth")
@RestController
class AuthController (

) {

    val log = KotlinLogging.logger {  }

    @GetMapping("/login")
    fun login(session: HttpSession){
        session.setAttribute("principal", "pass")
    }

}