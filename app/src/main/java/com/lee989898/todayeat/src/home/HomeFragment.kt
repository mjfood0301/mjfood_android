package com.lee989898.todayeat.src.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentHomeBinding
import com.lee989898.todayeat.src.home.adapter.HomeRVAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val rv = binding.rankingListRv
        // 임시데이터
        val items = ArrayList<String>()
        items.add("a")
        items.add("b")
        items.add("c")
        items.add("d")
        items.add("e")
        items.add("f")
        items.add("g")

        val rvAdapter = HomeRVAdapter(items)
        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(context)

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


}