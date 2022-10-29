package com.example.batch.mapper.csv

import com.example.batch.dto.CsvMember


class CustomMemberCsvAggregator(

) : CsvLineAggregator<CsvMember> {

    override val headerNames: Array<String> =
        arrayOf("id", "email", "password", "role", "createAt", "updateAt", "deleteAt", "orderNo", "postTitles" )

}