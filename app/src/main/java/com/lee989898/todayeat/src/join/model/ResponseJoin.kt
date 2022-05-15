package com.lee989898.todayeat.src.join.model

data class ResponseJoin(
    val isSuccess: Boolean,
    val code: Int,
    val message: List<String>,
    val result: String
)