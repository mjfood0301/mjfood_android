package com.lee989898.todayeat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee989898.todayeat.databinding.ActivityMainBinding
import com.lee989898.todayeat.src.mylist.MyListActivity
import com.lee989898.todayeat.src.profile.ProfileActivity
import com.lee989898.todayeat.src.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}