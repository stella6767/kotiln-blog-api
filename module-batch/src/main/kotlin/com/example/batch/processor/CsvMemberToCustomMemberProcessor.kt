package com.example.batch.processor

import com.example.batch.dto.CsvMember
import com.example.batch.dto.CustomMember
import com.example.batch.dto.JavaBatchDto
import mu.KotlinLogging
import org.jeasy.random.EasyRandom
import org.springframework.batch.item.ItemProcessor


/**
 * Processor는 Reader에서 읽은 내용을 가공하는 역할
 */

class CsvMemberToCustomMemberProcessor(

) : ItemProcessor<CsvMember, CustomMember> {

    private val log = KotlinLogging.logger { }

    private var easyRandom = EasyRandom()


    override fun process(item: CsvMember): CustomMember {

        log.debug { "CsvMember=>$item" }
        //easyRandom.nextObject(CustomMember::class.java)
        return item.toCustomMember()

    }
}