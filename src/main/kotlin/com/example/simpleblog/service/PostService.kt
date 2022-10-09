package com.example.simpleblog.service

import com.example.simpleblog.config.aws.S3FileUploader
import com.example.simpleblog.domain.post.*
import com.example.simpleblog.util.dto.SearchCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.net.URL


@Service
class PostService(
    private val postRepository:PostRepository,
    private val s3FileUploader: S3FileUploader
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

    fun savePostImg(image: MultipartFile): URL? {

        /**
         * AWS S3에 올릴 건데 지금 S3가 없죠.
         *
         */

        return s3FileUploader.upload(image)
    }


}