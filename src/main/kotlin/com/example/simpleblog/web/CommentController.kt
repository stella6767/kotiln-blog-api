package com.example.simpleblog.web

import com.example.simpleblog.domain.commenet.CommentSaveReq
import com.example.simpleblog.service.CommentService
import com.example.simpleblog.util.value.CmResDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService
) {


    @PostMapping("/comment")
    fun saveComment(dto: CommentSaveReq): CmResDto<*> {

        return CmResDto(HttpStatus.OK, "save comment", commentService.saveComment(dto))
    }


}