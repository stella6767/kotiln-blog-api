package com.example.web.service

import com.example.common.domain.SearchCondition
import com.example.common.domain.post.*
import com.example.common.domain.dto.PostRes
import com.example.web.config.dto.PostSaveReq
import com.example.web.service.file.FileUploader

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.net.URL


@Service
class PostService(
    private val postRepository:PostRepository,
    private val fileUploader: FileUploader,
) {


    //@PreAuthorize("hasRole('ADMIN')")
    //@Secured(*["ROLE_SUPER", "ROLE_ADMIN"])
    @Transactional(readOnly = true)
    fun findPosts(pageable: Pageable, searchCondition: SearchCondition): Page<PostRes> {
        return postRepository.findPosts(pageable, searchCondition).map { it.toDto() }
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

    fun savePostImg(image: MultipartFile): String {

        /**
         * AWS S3에 올릴 건데 지금 S3가 없죠.
         *
         */

        return fileUploader.upload(image)
    }


}