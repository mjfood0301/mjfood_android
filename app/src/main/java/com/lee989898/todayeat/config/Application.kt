package com.lee989898.todayeat.config

import android.app.Application
import android.content.SharedPreferences


class Application : Application() {
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var joinSharedPreferences: SharedPreferences
        lateinit var tokenSharedPreferences: SharedPreferences

        val X_ACCESS_TOKEN = "x-access-token"
    }

    override fun onCreate() {
        super.onCreate()

        joinSharedPreferences =
            applicationContext.getSharedPreferences("join", MODE_PRIVATE)
        tokenSharedPreferences =
            applicationContext.getSharedPreferences("token", MODE_PRIVATE)
    }
}

