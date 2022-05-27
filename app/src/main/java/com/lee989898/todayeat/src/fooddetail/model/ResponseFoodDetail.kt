package com.lee989898.todayeat.src.fooddetail.model

data class ResponseFoodDetail(
    val isSuccess : Boolean,
    val code: Int,
    val message: List<String>,
    val result: GetFoodRes
)

data class GetFoodRes(
    val id: Int,
    val image: String,
    val info: String,
    val menus: List<MenuDto>,
    val name: String
)

data class MenuDto(
    val locationX: Int,
    val locationY: Int,
    val menuName: String
)