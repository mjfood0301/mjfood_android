package com.lee989898.todayeat.src.mylike.model

data class ResponseLike(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: List<GetLikeUser>
)

data class GetLikeUser(
    val image: String,
    val name: String,
    val storeId: Int
)

