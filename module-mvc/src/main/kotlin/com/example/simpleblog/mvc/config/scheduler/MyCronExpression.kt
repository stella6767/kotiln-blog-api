package com.example.simpleblog.mvc.config.scheduler

object MyCronExpression {


    const val oneHour = "0 0 0/1 * * *"
    const val oneMinute = "0 0/1 0 * * *"
    const val tenSecond = "*/10 * * * * *"

}