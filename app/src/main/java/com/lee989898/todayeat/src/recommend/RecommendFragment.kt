package com.lee989898.todayeat.src.recommend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentRecommendBinding


class RecommendFragment : Fragment() {

    private lateinit var binding: FragmentRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendBinding.inflate(inflater, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recommendFragment_to_homeFragment)
        }

        binding.likeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recommendFragment_to_likeFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_recommendFragment_to_profileFragment)
        }

        return binding.root
    }


}