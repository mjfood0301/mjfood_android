package com.lee989898.todayeat.src.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}