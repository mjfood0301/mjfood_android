package com.lee989898.todayeat.src.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.databinding.ActivitySearchBinding
import com.lee989898.todayeat.src.login.model.ResponseKakao
import com.lee989898.todayeat.src.main.MainActivity
import com.lee989898.todayeat.src.search.adapter.SearchData
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter
import com.lee989898.todayeat.src.search.model.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    private var items = mutableListOf<SearchData>()
    private lateinit var adapter: SearchRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.ivSearch.setOnClickListener {
            getSearchNetWork()
            initRecycler()
        }

        binding.searchMapIv.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }



    }

    private fun initRecycler() {
        adapter = SearchRVAdapter(items)
        binding.searchResultRv.adapter = adapter
        binding.searchResultRv.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : SearchRVAdapter.ItemCLick {
            override fun onClick(view: View, position: Int) {
                Toast.makeText(this@SearchActivity, items[position].image, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getSearchNetWork() {

        val call =
            ServiceCreator.searchService.getSearchResult(binding.searchSearchEt.text.toString())

        call.enqueue(object : Callback<ResponseSearch> {

            override fun onResponse(
                call: Call<ResponseSearch>,
                response: Response<ResponseSearch>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@SearchActivity, "검색 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseSearch>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }
}