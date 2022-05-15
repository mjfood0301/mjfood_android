package com.lee989898.todayeat.src.profile.modifymodel

import retrofit2.Call
import retrofit2.http.*

interface ModifyService {

    @FormUrlEncoded
    @PATCH("api/users/{userId}")
    fun patchModify(
        @Header("x-access-token") token: String,
        @Path("userId") userId: Int,
        @Field("dislikes") dislikes: MutableList<Int>
    ): Call<ResponseModify>
}