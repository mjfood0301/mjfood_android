package com.lee989898.todayeat.src.login.model

import retrofit2.Call
import retrofit2.http.GET

interface KakaoService {

    @GET("oauth2/authorization/kakao")
    fun getKakaoToken(): Call<ResponseKakao>
}