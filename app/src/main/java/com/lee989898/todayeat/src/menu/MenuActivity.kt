package com.lee989898.todayeat.src.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}