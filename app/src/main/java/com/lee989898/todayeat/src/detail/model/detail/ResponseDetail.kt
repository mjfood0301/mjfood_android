package com.lee989898.todayeat.src.detail.model.detail

import java.text.DecimalFormat


data class ResponseDetail(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: GetStoreRes
)

data class GetStoreRes(
    val image: String,
    val likesCount: Int,
    val locationX: Double,
    val locationY: Double,
    val name: String,
    val reviews: List<ReviewDto>,
    val storeId: Int
)

data class ReviewDto(
    val content: String,
    val image: String,
    val rate: Int,
    val reviewId: Int,
    val userName: String
)