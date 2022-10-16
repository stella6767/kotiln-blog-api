package com.example.simpleblog.service

import com.example.simpleblog.domain.commenet.Comment
import com.example.simpleblog.domain.commenet.CommentRepository
import com.example.simpleblog.domain.commenet.CommentSaveReq
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRepository
import com.example.simpleblog.domain.post.Post
import com.example.simpleblog.domain.post.PostRepository
import com.example.simpleblog.setup.MockitoHelper
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.junit.jupiter.MockitoSettings
import org.mockito.quality.Strictness
import java.util.*


//@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension::class) //테스트 클래스가 Mockito를 사용함을 의미
internal class ServiceTest {

    private val log = KotlinLogging.logger {  }

    @Mock //Mocking용 객체, 테스트 런타임시 요게 주입
    private lateinit var commentRepository:CommentRepository

    @Mock
    private lateinit var postRepository: PostRepository

    @Mock
    private lateinit var memberRepository: MemberRepository

    @InjectMocks // @Mock 객체가 주입될 클래스
    private lateinit var commentService: CommentService

    @BeforeEach
    internal fun setUp() {
        //MockitoAnnotations.initMocks(this)
    }

    @Test
    fun diTest(){
        log.info { "${this.commentService}" }
    }


    @Test
    fun saveCommentTest() {

        val dto = CommentSaveReq(
            memberId = 1,
            content = "test content",
            postId = 1,
            idAncestor = null
        )
        val post= Optional.ofNullable(Post(
            id = 1,
            title = "title",
            content = "content",
            member = Member.createFakeMember(1)
        ))




        val comment:Comment = dto.toEntity(post.orElseThrow())

        Mockito.`when`(postRepository.findById(dto.postId)).thenReturn(post)
        Mockito.`when`(commentRepository.saveComment(MockitoHelper.anyObject())).thenReturn(comment)

        //Mockito.`when`(commentRepository.saveCommentClosure(1, dto.idAncestor)).thenReturn(anyInt())
        //Mockito.doNothing().`when`(commentRepository).saveCommentClosure(1, dto.idAncestor)
        //Mockito.`when`(commentRepository.saveCommentClosure(1, dto.idAncestor)).thenReturn(any())
//        Mockito.doThrow(IllegalStateException("failed"))
//            .`when`(commentRepository).saveCommentClosure(1, dto.idAncestor)
        //BDDMockito.willDoNothing().given(commentRepository).saveCommentClosure(1, dto.idAncestor)

        val saveComment = commentService.saveComment(dto)
//        log.info { "saveComment==>$saveComment" }

        Assertions.assertEquals(comment.content, saveComment.content)

    }


}