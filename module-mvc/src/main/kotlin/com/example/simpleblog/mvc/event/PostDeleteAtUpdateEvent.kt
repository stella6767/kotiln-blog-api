package com.example.simpleblog.mvc.event

import java.time.LocalDateTime

data class PostDeleteAtUpdateEvent(
    override val timeStamp: LocalDateTime
) : Event(timeStamp)
