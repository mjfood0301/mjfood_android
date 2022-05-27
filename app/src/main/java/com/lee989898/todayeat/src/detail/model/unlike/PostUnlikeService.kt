package com.lee989898.todayeat.src.detail.model.unlike

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PostUnlikeService {

    @POST("api/likes/status/{userId}/{storeId}")
    fun postUnlike(
        @Header("x-access-token") token: String,
        @Path("storeId") storeId: Int,
        @Path("userId") userId: Int
    ): Call<ResponseUnlike>

}