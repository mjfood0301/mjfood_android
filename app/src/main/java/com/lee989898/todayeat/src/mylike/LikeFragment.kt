package com.lee989898.todayeat.src.mylike

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivitySearchBinding
import com.lee989898.todayeat.databinding.FragmentLikeBinding
import com.lee989898.todayeat.src.detail.DetailActivity
import com.lee989898.todayeat.src.fooddetail.FoodDetailActivity
import com.lee989898.todayeat.src.fooddetail.model.ResponseFoodDetail
import com.lee989898.todayeat.src.mylike.adapter.LikeData
import com.lee989898.todayeat.src.mylike.adapter.LikeRVAdapter
import com.lee989898.todayeat.src.mylike.model.ResponseLike
import com.lee989898.todayeat.src.search.adapter.SearchData
import com.lee989898.todayeat.src.search.adapter.SearchRVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding
    private lateinit var adapter: LikeRVAdapter

    private var items = mutableListOf<LikeData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)

        val sharedPreferences = Application.tokenSharedPreferences
        val token = sharedPreferences.getString("kakaotoken", "")
        val userId = sharedPreferences.getInt("id", 0)

        if(token == ""){
            Toast.makeText(activity, "로그인 해야 사용할 수 있는 기능입니다.", Toast.LENGTH_SHORT).show()
        }else{
            getLikeListNetwork(token.toString(), userId)
            initRecycler()
        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_likeFragment_to_homeFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_likeFragment_to_profileFragment)
        }

        binding.recommendTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_likeFragment_to_recommendFragment)
        }

        return binding.root
    }

    private fun initRecycler() {

        adapter = LikeRVAdapter()
        binding.myListRv.adapter = adapter
        binding.myListRv.layoutManager = LinearLayoutManager(activity)
        adapter.data = items

        adapter.itemClick = object : LikeRVAdapter.ItemCLick {

            override fun onClick(view: View, likeData: LikeData) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("storeId", likeData.storeId)
                startActivity(intent)
            }
        }
    }

    private fun getLikeListNetwork(token: String, userId: Int) {


        val call =
            ServiceCreator.likeListService.getLikeList(token, userId)

        call.enqueue(object : Callback<ResponseLike> {

            override fun onResponse(call: Call<ResponseLike>, response: Response<ResponseLike>) {
                if (response.isSuccessful) {
                    val result = response.body()?.result
                    for(i in 0 until result?.size!!.toInt())
                        result[i].let {
                            items.add(LikeData(it.image, it.name, it.storeId))
                        }
                    adapter.data = items

                } else {
                    Toast.makeText(activity, "디테일 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseLike>, t: Throwable) {
                Log.e("NetworkTest22", "error:$t")
            }

        })
    }


}