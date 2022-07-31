package com.example.simpleblog.api

import com.example.simpleblog.domain.member.MemberSaveReq
import com.example.simpleblog.domain.post.PostSaveReq
import com.example.simpleblog.service.PostService
import com.example.simpleblog.util.value.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


//@RequestMapping("/post")
@RestController
class PostController(
    private val postService: PostService
) {


    @GetMapping("/posts")
    fun findPosts(@PageableDefault(size = 10) pageable: Pageable): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "find posts", postService.findPosts(pageable));
    }

    @GetMapping("/post/{id}")
    fun findById(@PathVariable id:Long): CmResDto<Any> {

        return CmResDto(HttpStatus.OK, "find Post by id", postService.findById(id))
    }


    @DeleteMapping("/post/{id}")
    fun deleteById(@PathVariable id:Long): CmResDto<Any> {

        return CmResDto(HttpStatus.OK, "delete Post by id", postService.deletePost(id))
    }


    @PostMapping("/post")
    fun save(@Valid @RequestBody dto: PostSaveReq): CmResDto<*> {

        return CmResDto(HttpStatus.OK, "save Post", postService.save(dto))
    }








}