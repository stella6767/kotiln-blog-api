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
@Transactional
class PostService(
    private val postRepository: PostRepository,
    private val localFileUploaderServiceImpl: FileUploaderService,
    private val cacheService: CacheService,
) {

    private val log = KotlinLogging.logger { }

    fun save(dto: PostSaveReq): PostRes {
        return postRepository.save(dto.toEntity()).toDto()
    }

    fun deletePost(id: Long) {
        return postRepository.deleteById(id)
    }


    fun savePostImg(image: MultipartFile): String {
        return localFileUploaderServiceImpl.upload(image)
    }


    @EventListener
    fun updateDeleteAtEventHandler(event: PostDeleteAtUpdateEvent) {

        log.info { "event 구독==> $event" }
        val allPosts = postRepository.findAll()
        val filterPosts: List<Post> = allPosts.filter {
            it.deleteAt != null && it.reservateAt != null && LocalDateTime.now().isAfter(it.reservateAt)
        }

        val ids = filterPosts.map {
            it.id
        }

        if (ids.isNotEmpty()) {
            log.info { "update 공개 execute count=> ${postRepository.updateDeleteAtByReservateAt(ids)}" }
        }
    }

}