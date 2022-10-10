package com.example.web.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val code:String,
    val message:String
) {

    INVALID_INPUT_VALUE("COO1", " Invalid input value"),
    ENTITY_NOT_FOUND("COO2", "Entity not found")

}