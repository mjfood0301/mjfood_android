package com.lee989898.todayeat.src.detail.model.review

import retrofit2.Call
import retrofit2.http.*

interface ReviewService {

    @POST("api/reviews/")
    fun postReview(
        @Header("x-access-token") token: String,
        @Body requestReview: RequestReview
    ): Call<ResponseReview>
}
