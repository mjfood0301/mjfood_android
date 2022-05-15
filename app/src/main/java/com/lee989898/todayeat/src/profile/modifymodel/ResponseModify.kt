package com.lee989898.todayeat.src.profile.modifymodel

data class ResponseModify(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: String
)
