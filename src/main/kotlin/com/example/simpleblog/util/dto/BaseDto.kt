package com.example.simpleblog.util.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.time.LocalDateTime


@JsonPropertyOrder("id", "createAt", "updateAt")
open class BaseDto(
    var id :Long = 0,
    var createAt: LocalDateTime = LocalDateTime.now(),
    var updateAt: LocalDateTime = LocalDateTime.now()
)