package com.example.simpleblog.service

import com.example.simpleblog.domain.commenet.Comment
import com.example.simpleblog.domain.commenet.CommentRepository
import com.example.simpleblog.domain.commenet.CommentRes
import com.example.simpleblog.domain.commenet.CommentSaveReq
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRepository
import com.example.simpleblog.domain.post.PostRepository
import com.example.simpleblog.exception.MemberNotFoundException
import com.example.simpleblog.exception.PostNotFoundException
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
) {

    private val log = KotlinLogging.logger { }

    @Transactional
    fun saveComment(dto: CommentSaveReq): CommentRes {
        val post =
            postRepository.findById(dto.postId).orElseThrow { throw PostNotFoundException(dto.postId.toString()) }

        val comment: Comment = commentRepository.saveComment(dto.toEntity(post = post))
        //commentRepository.saveCommentClosure(comment.id, dto.idAncestor)

        log.info { "comment ===> $comment,  post===>$post" }

        return comment.toDto()
    }


}