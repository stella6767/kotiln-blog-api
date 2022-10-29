package com.example.batch.processor

import com.example.batch.dto.CustomMember
import com.example.simpleblog.core.domain.member.Member
import mu.KotlinLogging
import org.jeasy.random.EasyRandom
import org.springframework.batch.item.ItemProcessor


/**
 * Processor는 Reader에서 읽은 내용을 가공하는 역할
 */

class CustomMemberProcessor(

) : ItemProcessor<CustomMember,CustomMember> {

    private val log = KotlinLogging.logger {  }

    private var easyRandom = EasyRandom()


    override fun process(item: CustomMember): CustomMember {

        item.revisePostTitles()

        //easyRandom.nextObject(CustomMember::class.java)
        return item
    }
}