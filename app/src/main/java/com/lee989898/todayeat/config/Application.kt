package com.lee989898.todayeat.config

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Application : Application() {
    val API_URL = "http://3.34.223.58:9000"
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var joinSharedPreferences: SharedPreferences
        lateinit var tokenSharedPreferences: SharedPreferences
        lateinit var sRetrofit: Retrofit
        // JWT Token Header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
    }

    override fun onCreate() {
        super.onCreate()

//        KakaoSdk.init(this, "e355d004b5a2901c09d0626d9f643ad9")

        joinSharedPreferences =
            applicationContext.getSharedPreferences("join", MODE_PRIVATE)
        tokenSharedPreferences =
            applicationContext.getSharedPreferences("token", MODE_PRIVATE)
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.



    }
    private fun initRetrofitInstance(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor) // API Response 로그 작성용
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()
        Log.d("lateinit", "sdfsdlf")
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

