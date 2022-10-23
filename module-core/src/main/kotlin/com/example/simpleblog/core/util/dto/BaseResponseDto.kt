package com.example.simpleblog.core.util.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.LocalDateTime


@JsonPropertyOrder("id", "createAt", "updateAt", "isShow")
open class BaseResponseDto(
    var id :Long = 0,
    var createAt: LocalDateTime = LocalDateTime.now(),
    var updateAt: LocalDateTime = LocalDateTime.now(),
    var isShow:Boolean = true,
)