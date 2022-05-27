package com.lee989898.todayeat.src.detail.model.detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET("api/stores/{storeId}")
    fun getDetailResult(@Path("storeId")storeId: Int): Call<ResponseDetail>

}