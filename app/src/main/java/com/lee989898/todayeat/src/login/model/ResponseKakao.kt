package com.lee989898.todayeat.src.login.model

data class ResponseKakao(
    val isSuccess : Boolean,
    val code: Int,
    val message: String,
    val result: Object
)

data class Object(
    val userId: Int,
    val jwt: String
)