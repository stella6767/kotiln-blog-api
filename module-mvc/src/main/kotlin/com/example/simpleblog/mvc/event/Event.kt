package com.example.simpleblog.mvc.event

import java.time.LocalDateTime

abstract class Event(
    open val timeStamp:LocalDateTime
) {

}