package com.lee989898.todayeat.src.fooddetail.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodDetailService {

    @GET("api/foods/{foodId}")
    fun getFoodDetailResult(@Path("foodId")foodId: Int): Call<ResponseFoodDetail>
}