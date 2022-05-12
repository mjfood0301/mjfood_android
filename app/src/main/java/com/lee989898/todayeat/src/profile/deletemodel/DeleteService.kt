package com.lee989898.todayeat.src.profile.deletemodel

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface DeleteService {

    @PATCH("api/users/status/{userId}")
    fun patchDelete(
        @Header("x-access-token") token: String, @Path("userId") userId: Int
    ): Call<ResponseDelete>
}