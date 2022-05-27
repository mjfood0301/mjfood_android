package com.lee989898.todayeat.src.detail.model

data class RequestReview(
    val content: String,
    val rate: Int,
    val storeId: Int,
    val userId: Int
)