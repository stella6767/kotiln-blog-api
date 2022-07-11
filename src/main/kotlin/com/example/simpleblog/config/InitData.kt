package com.example.simpleblog.config

import com.example.simpleblog.domain.member.JoinReq
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.member.MemberRepository
import com.example.simpleblog.domain.post.Post
import com.example.simpleblog.domain.post.PostRepository
import com.example.simpleblog.domain.post.PostSaveReq
import io.github.serpro69.kfaker.faker
import mu.KotlinLogging
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class InitData(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository

) {

    val faker = faker { }
    private val log = KotlinLogging.logger {}


    @EventListener(ApplicationReadyEvent::class)
    private fun init(){

        val members = generateMembers()
        memberRepository.saveAll(members)
        val posts = generatePosts()
        postRepository.saveAll(posts)

    }

    private fun generateMembers(): MutableList<Member> {
        val members = mutableListOf<Member>()
        for (i in 1..100) {
            val member = generateMember()
            log.info { "insert $member" }
            members.add(member)
        }
        return members
    }

    private fun generateMember() = JoinReq(
            email = faker.internet.safeEmail(),
            password = "1234",
    ).toEntity()

    private fun generatePosts(): MutableList<Post> {
        val members = mutableListOf<Post>()
        for (i in 1..100) {
            val post = generatePost()
            log.info { "insert $post" }
            members.add(post)
        }
        return members
    }
    private fun generatePost() = PostSaveReq(
            1L,
            title = faker.friends.characters(),
            content = faker.random.randomString(length = 250)
    ).toEntity()


}