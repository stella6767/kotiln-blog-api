package com.example.batch.processor

import com.example.simpleblog.core.domain.member.Member
import mu.KotlinLogging
import org.springframework.batch.item.ItemProcessor

/**
 * Processor는 Reader에서 읽은 내용을 가공하는 역할
 */

class MemberProcessor(

) : ItemProcessor<Member,Member> {

    private val log = KotlinLogging.logger {  }

    override fun process(item: Member): Member {
        val member = item
        log.info { member.email }
        return member
    }
}