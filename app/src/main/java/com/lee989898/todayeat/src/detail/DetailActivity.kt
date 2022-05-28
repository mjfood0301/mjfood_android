package com.lee989898.todayeat.src.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityDetailBinding
import com.lee989898.todayeat.src.detail.adapter.CommentRVAdapter
import com.lee989898.todayeat.src.detail.model.detail.ResponseDetail
import com.lee989898.todayeat.src.detail.model.like.ResponsePostLike
import com.lee989898.todayeat.src.detail.model.review.RequestReview
import com.lee989898.todayeat.src.detail.model.review.ResponseReview
import com.lee989898.todayeat.src.detail.model.review.ReviewData
import com.lee989898.todayeat.src.detail.model.unlike.ResponseUnlike
import com.lee989898.todayeat.src.fooddetail.FoodDetailActivity
import com.lee989898.todayeat.src.search.adapter.SearchData
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: CommentRVAdapter
    var num = 0

    private var items = mutableListOf<ReviewData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        super.onCreate(savedInstanceState)

        initRecycler()



        val sharedPreferences = Application.tokenSharedPreferences
        val jwt = sharedPreferences.getString("kakaotoken", "")
        val id = sharedPreferences.getInt("id", 0)



        binding.detailHeartOffIv.setOnClickListener {
            postLikeNetWork(jwt.toString(),40 ,id)
            binding.detailHeartOffIv.visibility = View.GONE
            binding.detailHeartOnIv.visibility = View.VISIBLE
        }

        binding.detailHeartOnIv.setOnClickListener {
            postUnlikeNetWork(jwt.toString(),40 ,id)
            binding.detailHeartOffIv.visibility = View.VISIBLE
            binding.detailHeartOnIv.visibility = View.GONE
        }

//        val storeId = intent.getIntExtra("stordId", 0)
//        getDetailNetWork(storeId)
        binding.btReview.setOnClickListener {
            postReviewNetWork(jwt.toString(), id)

            getDetailNetWork()
        }

        getDetailNetWork()
    }


    private fun initRecycler() {

        adapter = CommentRVAdapter()
        binding.detailReviewRv.adapter = adapter
        binding.detailReviewRv.layoutManager = LinearLayoutManager(this)
    }

    private fun getDetailNetWork() {

        val call = ServiceCreator.detailService.getDetailResult(40)

        call.enqueue(object : Callback<ResponseDetail> {

            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (num > 0){
                    val result = response.body()?.result
                    if(result?.likesCount!! > 0){
                        binding.detailHeartOffIv.visibility = View.GONE
                        binding.detailHeartOnIv.visibility = View.VISIBLE
                    }
                    binding.detailNameTv.text = result?.name
                    Glide.with(binding.detailFoodIv)
                        .load(result?.image)
                        .into(binding.detailFoodIv)
                        items.add(ReviewData(
                            result!!.reviews[result.reviews.size-1].content,
                            result.reviews[result.reviews.size-1].image,
                            result.reviews[result.reviews.size-1].rate,
                            result.reviews[result.reviews.size-1].userName
                        ))

                    adapter.data = items
                }
                else if (response.isSuccessful) {
                    ++num
                    val result = response.body()?.result
                    if(result?.likesCount!! > 0){
                        binding.detailHeartOffIv.visibility = View.GONE
                        binding.detailHeartOnIv.visibility = View.VISIBLE
                    }
                    binding.detailNameTv.text = result?.name
                    Glide.with(binding.detailFoodIv)
                        .load(result?.image)
                        .into(binding.detailFoodIv)
                    for(i in 0 until result?.reviews?.size!!){
                        items.add(ReviewData(
                            result.reviews[i].content,
                            result.reviews[i].image,
                            result.reviews[i].rate,
                            result.reviews[i].userName
                        ))
                    }
                    adapter.data = items
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

    private fun postReviewNetWork(jwt: String, userId: Int) {

        // 임시데이터
        val call =
            ServiceCreator.reviewService.postReview(jwt, RequestReview(binding.etReview.text.toString(),1,40, userId))

        call.enqueue(object : Callback<ResponseReview> {

            override fun onResponse(
                call: Call<ResponseReview>,
                response: Response<ResponseReview>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@DetailActivity, "리뷰 쓰기 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseReview>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }

    private fun postLikeNetWork(jwt: String, storeId: Int,  userId: Int) {

        // 임시데이터
        val call =
            ServiceCreator.postLikeService.postLike(jwt, storeId, userId)

        call.enqueue(object : Callback<ResponsePostLike> {

            override fun onResponse(
                call: Call<ResponsePostLike>,
                response: Response<ResponsePostLike>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@DetailActivity, "좋아요 누르기 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponsePostLike>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }

    private fun postUnlikeNetWork(jwt: String, storeId: Int,  userId: Int) {

        // 임시데이터
        val call =
            ServiceCreator.postUnlikeService.postUnlike(jwt, storeId, userId)

        call.enqueue(object : Callback<ResponseUnlike> {

            override fun onResponse(
                call: Call<ResponseUnlike>,
                response: Response<ResponseUnlike>
            ) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
                } else {
                    Toast.makeText(this@DetailActivity, "싫어요 누르기 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseUnlike>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }
}