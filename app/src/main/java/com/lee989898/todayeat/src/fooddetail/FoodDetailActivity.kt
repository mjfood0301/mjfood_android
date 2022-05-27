package com.lee989898.todayeat.src.fooddetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.databinding.ActivityFoodDetailBinding
import com.lee989898.todayeat.src.fooddetail.model.ResponseFoodDetail
import com.lee989898.todayeat.src.search.adapter.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_detail)

        val foodId = intent.getIntExtra("foodId", 0)
        getFoodDetailNetwork(foodId)

    }

    private fun getFoodDetailNetwork(foodId: Int) {

        val call =
            ServiceCreator.foodDetailService.getFoodDetailResult(foodId)

        call.enqueue(object : Callback<ResponseFoodDetail> {

            override fun onResponse(
                call: Call<ResponseFoodDetail>,
                response: Response<ResponseFoodDetail>
            ) {
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    binding.tvFoodName.text = result?.name
                    Glide.with(this@FoodDetailActivity)
                        .load(result?.image)
                        .into(binding.ivFood)
                } else {
                    Toast.makeText(this@FoodDetailActivity, "디테일 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseFoodDetail>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }

}