package com.example.simpleblog.mvc.service


import com.example.simpleblog.core.domain.commenet.CommentRepository
import com.example.simpleblog.core.domain.member.Member
import com.example.simpleblog.core.domain.post.Post
import com.example.simpleblog.core.domain.post.PostRepository
import com.example.simpleblog.core.domain.post.PostType
import com.example.simpleblog.mvc.setup.MockitoHelper
import com.example.simpleblog.mvc.web.dto.CommentSaveReq

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
internal class ServiceTest {

    /**
     * https://www.inflearn.com/questions/441973
     * https://withhamit.tistory.com/138
     * https://stackoverflow.com/questions/59230041/argumentmatchers-any-must-not-be-null
     * https://tecoble.techcourse.co.kr/post/2020-09-19-what-is-test-double/
     */

    private val log = KotlinLogging.logger { }

    @Mock //mocking 용 객체, 테스트 런타임시 주입할 겁니다.
    private lateinit var commentRepository: CommentRepository

    @Mock
    private lateinit var postRepository: PostRepository

    @InjectMocks
    private lateinit var commentService: CommentService


    @Test
    fun mockDiTest() {
        log.info {
            """
            ${this.commentService}
        """.trimIndent()
        }
    }

    @Test
    fun saveCommentTest() {

        //given
        val dto = CommentSaveReq(
            memberId = 1,
            content = "test content",
            postId = 1,
            idAncestor = null
        )

        val post = Optional.ofNullable(
            Post(
                id = 1,
                title = "title",
                content = "content",
                member = Member.createFakeMember(2),
                reservateAt = null,
                postType = PostType.GOSSIP
            )
        )


        val expectedPost = post.orElseThrow()
        val comment = dto.toEntity(expectedPost)

        //stub
        Mockito.`when`(postRepository.findById(dto.postId)).thenReturn(post)
        Mockito.`when`(commentRepository.saveComment(MockitoHelper.anyObject())).thenReturn(comment)
        Mockito.`when`(commentRepository.saveCommentClosure(0, dto.idAncestor)).thenReturn(anyInt())

        val saveComment = commentService.saveComment(dto)


        //then
        Assertions.assertEquals(comment.content, saveComment.content)
        Assertions.assertEquals(comment.member.id, saveComment.member.id)
    }


}