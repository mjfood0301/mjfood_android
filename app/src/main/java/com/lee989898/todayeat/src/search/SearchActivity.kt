package com.lee989898.todayeat.src.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.databinding.ActivitySearchBinding
import com.lee989898.todayeat.src.detail.DetailActivity
import com.lee989898.todayeat.src.fooddetail.FoodDetailActivity
import com.lee989898.todayeat.src.search.adapter.SearchData
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter
import com.lee989898.todayeat.src.search.model.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: SearchRVAdapter

    private var items = mutableListOf<SearchData>()
    private var tagList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        initRecycler()

        binding.ivSearch.setOnClickListener {
            getSearchNetWork()
        }
    }

    private fun initRecycler() {

        adapter = SearchRVAdapter()
        binding.searchResultRv.adapter = adapter
        binding.searchResultRv.layoutManager = LinearLayoutManager(this)
        adapter.data = items

        adapter.itemClick = object : SearchRVAdapter.ItemCLick {
            override fun onClick(view: View, data: SearchData) {
                val intent = Intent(this@SearchActivity, FoodDetailActivity::class.java)
                intent.putExtra("foodId", data.foodId)
                startActivity(intent)
            }
        }
    }

    private fun getSearchNetWork() {

        val call =
            ServiceCreator.searchService.getSearchResult("떡볶이")
        // binding.searchSearchEt.text.toString()으로 나중에 변경

        call.enqueue(object : Callback<ResponseSearch> {

            override fun onResponse(
                call: Call<ResponseSearch>,
                response: Response<ResponseSearch>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    for(i in 0 until result?.size!!.toInt())
                        result[i].let {
                            for(i in 0 until result[i].tagList.size){
                                tagList.add(it.tagList[i].name)
                            }
                            items.add(SearchData(it.image, it.name, it.foodId, tagList))
                        }
                    adapter.data = items
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