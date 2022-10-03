package com.example.simpleblog.service

import com.example.simpleblog.domain.member.*
import com.example.simpleblog.domain.post.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class PostService(
    private val postRepository:PostRepository
) {


    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured(*["ROLE_SUPER", "ROLE_ADMIN"])
    @Transactional(readOnly = true)
    fun findPosts(pageable: Pageable): Page<PostRes> {
        return postRepository.findPosts(pageable).map { it.toDto() }
    }

    @Transactional
    fun save(dto: PostSaveReq): PostRes {
        return postRepository.save(dto.toEntity()).toDto()
    }

    @Transactional
    fun deletePost(id: Long){
        return postRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findById(id:Long): PostRes {
        return postRepository.findById(id).orElseThrow().toDto()
    }




}