package com.example.simpleblog.util.value

data class CmResDto<T>(
    val resultCode:T,
    var resultMsg:String,
    val data:T
){

    fun reflectVersion(apiVersion:String){
        this.resultMsg = "version: $apiVersion / ${this.resultMsg}"
    }

}