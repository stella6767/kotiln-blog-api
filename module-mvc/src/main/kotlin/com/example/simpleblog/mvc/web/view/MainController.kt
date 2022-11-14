package com.example.simpleblog.mvc.web.view

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class MainController(

) {

    private val log = KotlinLogging.logger {  }


    @GetMapping("/index")
    fun index(): String {
        log.info { "??" }
        return "index"
    }


    @GetMapping("/todo")
    fun todo(): String {
        return "todo/todo"
    }


    @PostMapping("/todo/create")
    fun createTodo(@RequestParam("new-todo") todo:String, model:Model): String {
        model.addAttribute("item", todo)
        return "todo/todo :: todo"
    }


    @DeleteMapping("/todo/delete")
    @ResponseBody
    fun deleteTodo(): String {

        return ""
    }



}