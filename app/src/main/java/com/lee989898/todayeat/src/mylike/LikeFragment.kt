package com.lee989898.todayeat.src.mylike

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentLikeBinding
import com.lee989898.todayeat.src.mylike.adapter.LikeRVAdapter


class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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


}