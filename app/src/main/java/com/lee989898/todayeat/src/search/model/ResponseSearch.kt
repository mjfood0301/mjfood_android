package com.lee989898.todayeat.src.search.model

data class ResponseSearch(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: List<RecommendFoodRes>
)

data class RecommendFoodRes(
    val foodId: Int,
    val image: String,
    val name: String,
    val tagList: List<TagDto>
)

data class TagDto(
    val name: String,
    val tagId: Int
)