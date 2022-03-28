package com.lee989898.todayeat.src.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_search)
    }
}