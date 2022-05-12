package com.lee989898.todayeat

import com.google.gson.GsonBuilder
import com.lee989898.todayeat.config.XAccessTokenInterceptor
import com.lee989898.todayeat.src.join.model.JoinService
import com.lee989898.todayeat.src.login.model.KakaoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.scalars.ScalarsConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://kaydenserver.shop/"

    var gson = GsonBuilder().setLenient().create()



    private fun httpLoggin(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(httpLoggin()) // API Response 로그 작성용
        .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val kakaoService: KakaoService = retrofit.create(KakaoService::class.java)
    val joinService: JoinService = retrofit.create(JoinService::class.java)
}