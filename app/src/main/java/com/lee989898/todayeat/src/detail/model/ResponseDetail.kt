package com.lee989898.todayeat.src.detail.model

data class ResponseDetail(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: List<GetStoreRes>
)

data class GetStoreRes(
    val image: String,
    val likesCount: Int,
    val locationX: Int,
    val locationY: Int,
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