package com.example.batch.mapper.jdbc

import com.example.batch.dto.MemberWithPost
import mu.KotlinLogging
import org.springframework.jdbc.core.BeanPropertyRowMapper

class MemberWithPostMapper(

) : BeanPropertyRowMapper<MemberWithPost>(){

    private val log = KotlinLogging.logger {  }




}