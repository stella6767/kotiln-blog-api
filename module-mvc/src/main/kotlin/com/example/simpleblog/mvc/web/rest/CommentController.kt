package com.example.simpleblog.mvc.web.rest


import com.example.simpleblog.mvc.service.comment.CommentQueryService
import com.example.simpleblog.mvc.service.comment.CommentService
import com.example.simpleblog.mvc.web.dto.CommentSaveReq
import com.example.simpleblog.mvc.web.dto.common.CmResDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentController(
    private val commentService: CommentService,
    private val commentQueryService: CommentQueryService,
) {


    @PostMapping("/comment")
    fun saveComment(dto: CommentSaveReq): CmResDto<*> {

        return CmResDto(HttpStatus.OK, "save comment", commentService.saveComment(dto))
    }


    @GetMapping("/comment/{idAncestor}")
    fun findCommentByAncestorComment(@PathVariable idAncestor: Long): CmResDto<*> {
        return CmResDto(
            HttpStatus.OK,
            "find comment By idAncestor",
            commentQueryService.findCommentByAncestorComment(idAncestor)
        )
    }

}