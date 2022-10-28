package com.example.batch.processor

import com.example.batch.dto.MemberWithPost
import com.example.simpleblog.core.domain.member.Member
import mu.KotlinLogging
import org.jeasy.random.EasyRandom
import org.springframework.batch.item.ItemProcessor


/**
 * Processor는 Reader에서 읽은 내용을 가공하는 역할
 */

class MemberWithPostProcessor(

) : ItemProcessor<Member,MemberWithPost> {

    private val log = KotlinLogging.logger {  }

    private var easyRandom = EasyRandom()


    override fun process(item: Member): MemberWithPost {
        log.info { "!!  " + item }

        return easyRandom.nextObject(MemberWithPost::class.java)
    }
}