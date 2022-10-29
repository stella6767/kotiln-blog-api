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

    fun toCsvMember(): CsvMember {

        return CsvMember(
            id = this.id,
            this.email,
            this.password,
            this.role,
            this.createAt,
            this.updateAt,
            this.deleteAt ?: "없음",
            this.orderNo ?: 0,
            postTitles = this.postTitles?.replace(",".toRegex(), "#") ?: "없음"
        )

    }


}



data class CsvMember(
    val id:String,
    val email: String,
    val password: String,
    val role: String,
    var createAt: String,
    val updateAt: String,
    val deleteAt: String,
    val orderNo: Long,
    var postTitles:String,
){
    // https://stackoverflow.com/questions/69300083/bean-property-is-not-writeable-invalid-setter-method-in-kotlin
    //data class fieldSetmapper issue


    fun toCustomMember(): CustomMember {

        return CustomMember(
            id = this.id,
            this.email,
            this.password,
            this.role,
            this.createAt,
            this.updateAt,
            if (this.deleteAt == "없음") null else this.deleteAt,
            if (this.orderNo == 0L) null else this.orderNo,
            postTitles = this.postTitles?.replace("#".toRegex(), ",")
        )

    }


}