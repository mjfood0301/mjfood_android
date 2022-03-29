package com.lee989898.todayeat.src.ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.databinding.ActivityRankingBinding

class RankingActivity : AppCompatActivity() {

    lateinit var binding : ActivityRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}