package com.lee989898.todayeat.src.detail.model.like

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostLikeService {

    @POST("api/likes/{userId}/{storeId}")
    fun postLike(
        @Header("x-access-token") token: String,
        @Path("storeId") storeId: Int,
        @Path("userId") userId: Int
    ): Call<ResponsePostLike>
}