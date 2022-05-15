package com.lee989898.todayeat.src.profile.profilemodel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProfileService {

    @GET("api/users/{userId}")
    fun getProfile(
        @Header("x-access-token") token: String, @Path("userId") userId: Int,
    ): Call<ResponseProfile>
}