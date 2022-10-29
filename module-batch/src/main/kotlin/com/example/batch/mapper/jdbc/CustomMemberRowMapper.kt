package com.example.batch.mapper.jdbc

import com.example.batch.dto.CustomMember
import mu.KotlinLogging
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

/**
 * 참고
 * https://hello-bryan.tistory.com/335
 * https://stackoverflow.com/questions/62864662/spring-jdbc-beanpropertyrowmapper-with-kotlin
 * https://github.com/benelog/spring-jdbc-tips/blob/master/framework-compare.md
 * https://torbjorn.tistory.com/593
 *
 * RowMapper의 구현체로 대표적인 건 객체의 setter를 활용하는 BeanPropertyRowMapper,
 * java.util.Map으로 변환하는 ColumnMapRowMapper
 *
 * https://javabydeveloper.com/spring-jdbctemplate-pagination-examples/
 *
 */

class CustomMemberRowMapper(

) : RowMapper<CustomMember> {

    private val log = KotlinLogging.logger {  }

    override fun mapRow(rs: ResultSet, rowNum: Int): CustomMember {

        TODO("Not yet implemented")
    }


}