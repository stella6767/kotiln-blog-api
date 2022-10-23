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
@Transactional
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
) {

    private val log = KotlinLogging.logger { }

    fun saveComment(dto: CommentSaveReq): CommentRes {

        val post =
            postRepository.findById(dto.postId).orElseThrow { throw PostNotFoundException(dto.postId.toString()) }

        val comment: Comment = commentRepository.saveComment(dto.toEntity(post = post))

        commentRepository.saveCommentClosure(comment.id, dto.idAncestor)
        return comment.toDto()
    }


}