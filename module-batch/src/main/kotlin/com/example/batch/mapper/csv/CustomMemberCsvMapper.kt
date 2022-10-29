package com.example.batch.mapper.csv

import com.example.batch.dto.CustomMember

class CustomMemberCsvMapper(

) : CsvLineAggregator<CustomMember> {

    override val headerNames: Array<String> =
        arrayOf("id", "email", "password", "role", "createAt", "updateAt", "deleteAt", "orderNo", "postTitles" )

}