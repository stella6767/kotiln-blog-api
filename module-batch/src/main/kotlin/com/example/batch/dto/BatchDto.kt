package com.example.batch.dto



data class CustomMember(
    val id:String,
    val email: String,
    val password: String,
    val role: String,
    var createAt: String,
    val updateAt: String,
    val deleteAt: String?,
    val orderNo: Long?,
    var postTitles:String?,
){


    fun revisePostTitles(){

        this.postTitles = this.postTitles?.replace(",".toRegex(), "#")


    }

    override fun toString(): String {
        return "CustomMember(id='$id', email='$email', password='$password', role='$role', createAt='$createAt', updateAt='$updateAt', deleteAt=$deleteAt, orderNo=$orderNo, postTitles=$postTitles)"
    }
}