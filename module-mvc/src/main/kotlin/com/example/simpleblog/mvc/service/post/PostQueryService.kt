package com.example.simpleblog.mvc.service.post

import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.core.domain.post.PostRes

import com.example.simpleblog.core.util.dto.SearchCondition
import com.example.simpleblog.mvc.event.PostDeleteAtUpdateEvent
import com.example.simpleblog.mvc.exception.PostNotFoundException
import com.example.simpleblog.mvc.service.CacheService
import com.example.simpleblog.mvc.service.file.FileUploaderService
import com.example.simpleblog.mvc.web.dto.PostSaveReq
import mu.KotlinLogging
import org.springframework.context.event.EventListener

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime


@Service
@Transactional(readOnly = true)
class PostQueryService(
    private val postRepository: PostRepository,
    private val localFileUploaderServiceImpl:  FileUploaderService,
    private val cacheService: CacheService,
) {

    private val log = KotlinLogging.logger {  }

    fun autoCompletePostTitle(word:String): MutableList<String> {
        val complete = cacheService.addAutoCompletePostTitle().complete(word)
        val searchWords = mutableListOf<String>()
        for (autocompleteData in complete) {
            searchWords.add(autocompleteData.value)
        }
        return searchWords
    }

    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured(*["ROLE_SUPER", "ROLE_ADMIN"])
    fun findPosts(pageable: Pageable, searchCondition: SearchCondition): Page<PostRes> {
        return postRepository.findPosts(pageable, searchCondition).map { it.toDto() }
    }




    fun findById(id:Long): PostRes {
        return postRepository.findById(id).orElseThrow { throw PostNotFoundException(id.toString()) }.toDto()

    }



}