package com.lee989898.todayeat.src.ranking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.ActivityRankingBinding
import com.lee989898.todayeat.src.ranking.adapter.RankingRVAdapter
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter

class RankingActivity : AppCompatActivity() {

    lateinit var binding : ActivityRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rv : RecyclerView = findViewById(R.id.ranking_list_rv)

        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")
        items.add("d")
        items.add("e")
        items.add("f")
        items.add("g")

        val rvAdapter = RankingRVAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(this)

    }
}