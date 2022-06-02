package com.lee989898.todayeat.src.survey.model

import com.lee989898.todayeat.src.login.model.Object

data class ResponseRecommend(
    val isSuccess : Boolean,
    val code: Int,
    val message: String,
    val result: List<GetFoods>
)

data class GetFoods(
    val foodId: Int,
    val image: String,
    val name: String,
    val tagList: List<Tags>
)

data class Tags(
    val name: String,
    val tagId: Int
)