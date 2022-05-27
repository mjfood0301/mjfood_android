package com.lee989898.todayeat.src.mylike

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
import com.lee989898.todayeat.databinding.FragmentLikeBinding
import com.lee989898.todayeat.src.fooddetail.model.ResponseFoodDetail
import com.lee989898.todayeat.src.mylike.adapter.LikeRVAdapter
import com.lee989898.todayeat.src.mylike.model.ResponseLike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)

        val rv = binding.myListRv

        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")

        val rvAdapter = LikeRVAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(context)

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

    private fun getLikeListNetwork() {

        val call =
            ServiceCreator.likeListService.getLikeList("jwt", 3)

        call.enqueue(object : Callback<ResponseLike> {

            override fun onResponse(call: Call<ResponseLike>, response: Response<ResponseLike>) {
                if (response.isSuccessful) {
                    Log.d("Hello", response.body().toString())
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