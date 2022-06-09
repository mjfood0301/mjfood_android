package com.lee989898.todayeat.src.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.ActivityDetailBinding
import com.lee989898.todayeat.databinding.FragmentHomeBinding
import com.lee989898.todayeat.src.detail.DetailActivity
import com.lee989898.todayeat.src.detail.adapter.CommentRVAdapter
import com.lee989898.todayeat.src.detail.model.review.ReviewData
import com.lee989898.todayeat.src.home.adapter.HomeData
import com.lee989898.todayeat.src.home.adapter.HomeRVAdapter
import com.lee989898.todayeat.src.search.SearchActivity

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeRVAdapter

    private var items = mutableListOf<HomeData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        items = mutableListOf(
            HomeData("hh", "피자", 20),
            HomeData("hh", "스파게티", 30),
            HomeData("hh", "치킨", 24),
            HomeData("hh", "족발", 32),
            HomeData("hh", "족발", 32)
        )


        initRecycler()


        binding.rankingSearchIv.setOnClickListener {
            activity?.let {
//                val intent = Intent(activity, DetailActivity::class.java)
                val intent = Intent(activity, SearchActivity::class.java)
                startActivity(intent)
            }
        }

        binding.likeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_likeFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.recommendTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_recommendFragment)
        }

        return binding.root
    }

    private fun initRecycler() {

        adapter = HomeRVAdapter()
        adapter.data = items
        binding.rankingListRv.adapter = adapter
        binding.rankingListRv.layoutManager = LinearLayoutManager(activity)

    }


}