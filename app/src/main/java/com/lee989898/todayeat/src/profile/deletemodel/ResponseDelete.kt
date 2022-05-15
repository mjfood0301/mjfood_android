package com.lee989898.todayeat.src.profile.deletemodel

data class ResponseDelete(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: String
)
