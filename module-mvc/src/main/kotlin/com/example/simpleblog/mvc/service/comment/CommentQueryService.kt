package com.example.simpleblog.mvc.service.comment

import com.example.simpleblog.core.domain.commenet.Comment
import com.example.simpleblog.core.domain.commenet.CommentRepository
import com.example.simpleblog.core.domain.commenet.CommentRes
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.mvc.exception.PostNotFoundException
import com.example.simpleblog.mvc.web.dto.CommentSaveReq

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true)
class CommentQueryService(
    private val commentRepository: CommentRepository,
) {

    private val log = KotlinLogging.logger { }

    @Transactional(readOnly = true)
    fun findCommentByAncestorComment(idAncestor:Long): List<CommentRes> {

        return commentRepository.findCommentByAncestorComment(idAncestor).map { it.toDto() }
    }


}