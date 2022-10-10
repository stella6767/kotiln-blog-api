package com.example.web.config

import com.example.common.domain.commenet.CommentRepository
import com.example.common.domain.member.*
import com.example.common.domain.post.Post
import com.example.common.domain.post.PostRepository

import io.github.serpro69.kfaker.faker
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
) {

    val faker = faker { }
    private val log = KotlinLogging.logger {}



    @EventListener(ApplicationReadyEvent::class)
    private fun init(){

        //initMemberAndPosts()

    }

//    private fun initMemberAndPosts() {
//        val members = generateMembers(100)
//        memberRepository.saveAll(members)
//        val posts = generatePost(100)
//        postRepository.saveAll(posts)
//    }
//
//    private fun generateMembers(cnt:Int): MutableList<Member> {
//        val members = mutableListOf<Member>()
//
//        for (i in 1..cnt) {
//            val member = generateMember()
//            log.info { "insert $member" }
//            members.add(member)
//        }
//        return members
//    }
//
//    private fun generatePost(cnt:Int): MutableList<Post> {
//        val posts = mutableListOf<Post>()
//
//        for (i in 1..cnt) {
//            val post = generatePost()
//            log.info { "insert $post" }
//            posts.add(post)
//        }
//        return posts
//    }
//
//
//
//    private fun generateMember(): Member = LoginDto(
//            email = faker.internet.safeEmail(),
//            rawPassword = "1234",
//            role = Role.USER
//        ).toEntity()
//
//
//    private fun generatePost():Post = PostSaveReq(
//        title = faker.theExpanse.ships(),
//        content = faker.quote.matz(),
//        memberId = (0..100).random().toLong(),
//    ).toEntity()



}