package com.example.simpleblog.core.util.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class SearchCondition(
    @JsonProperty("searchType") val searchType: SearchType?,
    @JsonProperty("keyword") val keyword:String?
) {

    enum class SearchType {

        EMAIL, TITLE, CONTENT;

        companion object {

            @JsonCreator //JSON만 하는건가?
            fun from(s: String?): SearchType? {
                println("????? => $s")
                return SearchType.valueOf(s?.uppercase().toString())
            }
        }
    }

}


