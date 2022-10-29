package com.example.batch.writer

import com.example.batch.dto.CustomMember
import org.springframework.batch.item.ItemWriter
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.Timestamp
import java.sql.Types


class CustomJdbcMemberWriter(
    private val jdbcTemplate: JdbcTemplate
) : ItemWriter<CustomMember> {


    override fun write(items: MutableList<out CustomMember>) {
        jdbcTemplate.batchUpdate(
            """
                INSERT INTO 
                BatchMember 
                (email, password, role, postTitles, order_no, create_at, update_at, delete_at ) 
                VALUES 
                ( ?, ?, ?, ?, ?, ?, ?, ? )                                
            """.trimIndent(),
            items,
            items.size
        ) { ps, argument ->
            ps.setString(1, argument.email) // 쿼리의 ?의 순서대로 1번으로 할당되며 해당 쿼리 ? 대신 치환
            ps.setString(2, argument.password)
            ps.setString(3, argument.role)
            ps.setString(4, argument.postTitles)

            if (argument.orderNo == null) ps.setNull(5, Types.NULL )
            argument.orderNo?.let { ps.setLong(5, it) }
            ps.setTimestamp(6, Timestamp.valueOf(argument.createAt))
            ps.setTimestamp(7, Timestamp.valueOf(argument.updateAt))

            if (argument.deleteAt == null) ps.setNull(8, Types.NULL )
            argument.deleteAt?.let { ps.setTimestamp(8, Timestamp.valueOf(argument.deleteAt)) }
        }

//        for (item in items) {
//
//
//        }

    }
}