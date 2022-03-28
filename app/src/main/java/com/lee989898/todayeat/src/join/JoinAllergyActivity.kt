package com.lee989898.todayeat.src.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.MainActivity
import com.lee989898.todayeat.databinding.ActivityJoinAllergyBinding

class JoinAllergyActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinAllergyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinAllergyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.joinAllergyFinishColorIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}