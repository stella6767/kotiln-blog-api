package com.example.batch.mapper.csv

import com.example.batch.dto.CustomMember
import mu.KotlinLogging
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class CustomMemberFieldSetMapper(

) : FieldSetMapper<CustomMember> {

    private val log = KotlinLogging.logger {  }

    override fun mapFieldSet(fieldSet: FieldSet): CustomMember {
        TODO("Not yet implemented")
    }

}