package com.lee989898.todayeat.src.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.databinding.ActivityDetailBinding
import com.lee989898.todayeat.src.detail.adapter.CommentRVAdapter
import com.lee989898.todayeat.src.detail.model.RequestReview
import com.lee989898.todayeat.src.detail.model.ResponseDetail
import com.lee989898.todayeat.src.detail.model.ResponseReview
import com.lee989898.todayeat.src.search.model.ResponseSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rv : RecyclerView = findViewById(R.id.detail_review_rv)



        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")
        items.add("e")
        items.add("f")
        items.add("g")
        items.add("h")

        val rvAdapter = CommentRVAdapter(items)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

    }

    private fun getDetailNetWork() {

        val call =
            ServiceCreator.detailService.getDetailResult(0)

        call.enqueue(object : Callback<ResponseDetail> {

            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@DetailActivity, "디테일 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }

    private fun getReviewNetWork() {

        // 임시데이터
        val call =
            ServiceCreator.reviewService.postReview("312", RequestReview("hi",3,40,3))

        call.enqueue(object : Callback<ResponseReview> {

            override fun onResponse(
                call: Call<ResponseReview>,
                response: Response<ResponseReview>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@DetailActivity, "디테일 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseReview>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }
}