package com.lee989898.todayeat.src.detail.model.like

data class ResponsePostLike(
    val isSuccess: Boolean,
    val code: Int,
    val message: List<String>,
    val result: String
)
