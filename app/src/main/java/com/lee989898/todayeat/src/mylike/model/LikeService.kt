package com.lee989898.todayeat.src.mylike.model

import retrofit2.Call
import retrofit2.http.*

interface LikeService {

    @GET("api/likes/{userId}")
    fun getLikeList(
        @Header("x-access-token") token: String,
        @Path("userId") userId: Int
    ): Call<ResponseLike>

}