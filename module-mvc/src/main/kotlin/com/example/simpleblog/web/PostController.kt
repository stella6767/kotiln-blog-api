package com.example.simpleblog.web


import com.example.simpleblog.service.PostService
import com.example.simpleblog.util.dto.SearchCondition
import com.example.simpleblog.web.dto.PostSaveReq
import com.example.simpleblog.web.dto.common.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid



@RestController
class PostController(
    private val postService: PostService
) {


    @GetMapping("/posts")
    fun findPosts(@PageableDefault(size = 10) pageable: Pageable,
                  searchCondition: SearchCondition
    ): CmResDto<*> {
        return CmResDto(HttpStatus.OK, "find posts", postService.findPosts(pageable,searchCondition))
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


    @PostMapping("/post/img")
    fun savePostImg(image: MultipartFile): CmResDto<*>{
        return CmResDto(HttpStatus.OK, "save post Img", postService.savePostImg(image))
    }







}