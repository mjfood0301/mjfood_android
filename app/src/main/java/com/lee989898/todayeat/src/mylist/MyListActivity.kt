package com.lee989898.todayeat.src.mylist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.MainActivity
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.ActivityMyListBinding
import com.lee989898.todayeat.src.mylist.adapter.MyListRVAdapter

class MyListActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myListBackIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val rv : RecyclerView = findViewById(R.id.my_list_rv)

        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")

        val rvAdapter = MyListRVAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)

    }
}