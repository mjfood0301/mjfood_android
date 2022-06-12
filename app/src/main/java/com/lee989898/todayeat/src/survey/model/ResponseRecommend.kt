package com.lee989898.todayeat.src.survey.model

import java.io.Serializable

data class ResponseRecommend(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: List<FoodData>
)

data class FoodData (
    val foodId: Int,
    val image: String,
    val name: String,
    val tagList: List<Tags>
) : Serializable

data class Tags(
    val name: String,
    val tagId: Int
) : Serializable