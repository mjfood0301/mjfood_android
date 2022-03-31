package com.lee989898.todayeat.src.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.ActivitySearchBinding
import com.lee989898.todayeat.src.mylist.adapter.MyListRVAdapter
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_search)

        val rv : RecyclerView = findViewById(R.id.search_result_rv)

        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")
        items.add("d")
        items.add("e")
        items.add("f")
        items.add("g")

        val rvAdapter = SearchRVAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)


    }
}