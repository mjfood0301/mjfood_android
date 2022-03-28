package com.lee989898.todayeat.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.databinding.ActivityLankingBinding

class LankingActivity : AppCompatActivity() {

    lateinit var binding : ActivityLankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLankingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}