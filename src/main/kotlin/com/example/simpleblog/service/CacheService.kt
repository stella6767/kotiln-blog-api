package com.example.simpleblog.service

import com.example.simpleblog.config.cache.CacheType
import com.example.simpleblog.domain.post.PostRepository
import mu.KotlinLogging
import net.okihouse.autocomplete.repository.AutocompleteRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val autocompleteRepository: AutocompleteRepository,
    private val postRepository: PostRepository
    ) {

    private val log = KotlinLogging.logger { }

    @Cacheable(cacheNames = [CacheType.postName], key = "T(com.example.simpleblog.config.cache.CacheType).POST_NAME.cacheKey" )
    fun addAutoCompletePostTitle(): AutocompleteRepository {
        postRepository.findAll().forEach {
            autocompleteRepository.add(it.title)
        }
        return autocompleteRepository
    }

}