package com.lee989898.todayeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentHomeBinding
import com.lee989898.todayeat.databinding.FragmentLikeBinding


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

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_likeFragment_to_homeFragment)
        }

        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_likeFragment_to_profileFragment)
        }

        return binding.root
    }


}