package com.lee989898.todayeat.src.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.likeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_likeFragment)
        }

        binding.recommendTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_recommendFragment)
        }

        return binding.root
    }


}