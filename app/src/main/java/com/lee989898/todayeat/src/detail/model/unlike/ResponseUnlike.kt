package com.lee989898.todayeat.src.detail.model.unlike

data class ResponseUnlike(
    val isSuccess: Boolean,
    val code: Int,
    val message: List<String>,
    val result: String
)

