package com.example.simpleblog.mvc.web.dto.common

data class CmResDto<T>(
    val resultCode:T,
    var resultMsg:String,
    val data:T
){

    fun reflectVersion(apiVersion:String){
        this.resultMsg = "version: $apiVersion / ${this.resultMsg}"
    }

}