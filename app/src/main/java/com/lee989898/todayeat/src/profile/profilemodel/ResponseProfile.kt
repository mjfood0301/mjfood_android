package com.lee989898.todayeat.src.profile.profilemodel

data class ResponseProfile(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: GetUserRes
)

data class GetUserRes(
    val userDislikes: List<UserDislikeDto>
)

data class UserDislikeDto(
    val name: String,
    val userDislikeId: Int
)