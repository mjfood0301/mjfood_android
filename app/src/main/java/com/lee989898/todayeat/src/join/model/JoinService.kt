package com.lee989898.todayeat.src.join.model

import com.lee989898.todayeat.src.join.ResponseJoin
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface JoinService {

    @POST("api/users/{userId}")
    fun postJoin(
        @Header("x-access-token") token: String,
        @Path("userId") userId: Int,
        @Query("dislikes") dislikes: MutableList<Int>
    ): Call<ResponseJoin>
}