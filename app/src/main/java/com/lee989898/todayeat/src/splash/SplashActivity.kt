package com.lee989898.todayeat.src.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.TestActivity
import com.lee989898.todayeat.src.login.LoginActivity
import com.lee989898.todayeat.src.main.MainActivity
import com.lee989898.todayeat.src.recommend.RecommendWorldCup
import com.lee989898.todayeat.src.recommendResults.RecommendResults
import com.lee989898.todayeat.src.survey.resultActivity
import com.lee989898.todayeat.src.survey.surveyActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, TestActivity::class.java))
        }, 2000)

    }
}