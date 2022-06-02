package com.lee989898.todayeat.src.survey.model

import com.lee989898.todayeat.src.login.model.ResponseKakao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecommmendService{

    @GET("/api/recommend/")
    fun getRecommend(
        @Header("x-access-token") token: String,
        @Query("tags") tags: MutableList<Int>
    ): Call<ResponseRecommend>
}