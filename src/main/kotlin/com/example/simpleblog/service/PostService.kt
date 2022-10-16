package com.example.simpleblog.service

import com.example.simpleblog.domain.post.*
import com.example.simpleblog.exception.PostNotFoundException
import com.example.simpleblog.service.common.FileUploaderService
import com.example.simpleblog.service.common.LocalFileUploaderServiceImpl
import com.example.simpleblog.util.dto.SearchCondition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile


@Service
class PostService(
    private val postRepository:PostRepository,
    private val localS3FileUploaderServiceImpl: FileUploaderService,
    private val cacheService: CacheService,
) {


    @Transactional(readOnly = true)
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
        return postRepository.findById(id).orElseThrow { throw PostNotFoundException(id.toString()) }.toDto()

    }


    fun savePostImg(image: MultipartFile): String {
        return localS3FileUploaderServiceImpl.upload(image)
    }


}