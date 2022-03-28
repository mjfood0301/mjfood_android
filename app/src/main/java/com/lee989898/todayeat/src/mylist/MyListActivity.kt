package com.lee989898.todayeat.src.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.databinding.ActivityMyListBinding

class MyListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}