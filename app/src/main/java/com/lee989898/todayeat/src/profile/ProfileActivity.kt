package com.lee989898.todayeat.src.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}