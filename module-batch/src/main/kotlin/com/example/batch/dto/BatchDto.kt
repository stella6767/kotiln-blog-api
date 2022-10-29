package com.example.batch.dto


data class MemberWithPost(
    val id:String,
    val email: String,
    val password: String,
    val role: String,
    var createAt: String,
    val updateAt: String,
    val deleteAt: String?,
    val orderNo: Long?,
    //val postTitles:List<String>,
)


data class CsvMember(
    val id:String,
    val email: String,
    val password: String,
    val role: String,
    var createAt: String,
    val updateAt: String,
    val deleteAt: String?,
    val orderNo: Long?,
){

    override fun toString(): String {
        return "CsvMember(id='$id', email='$email', password='$password', role='$role', createAt='$createAt', updateAt='$updateAt', deleteAt=$deleteAt, orderNo=$orderNo)"
    }
}