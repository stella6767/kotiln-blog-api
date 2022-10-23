package com.example.simpleblog.core.domain

import mu.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource


@Transactional
@Repository
class JDBCTemplateRepository(
    @Suppress("SpringJavaInjectionPointsAutowiringInspection") private val dataSource: DataSource
) {

    private val jdbcTemplate = JdbcTemplate(dataSource)
    private val log = KotlinLogging.logger {  }

    fun updatePostOrderNo(id:Long){

        this.jdbcTemplate.update(
            "update post set order_no = ? where id=?",
            id, id
        )

    }



}