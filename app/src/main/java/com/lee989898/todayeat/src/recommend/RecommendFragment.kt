package com.lee989898.todayeat.src.recommend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentRecommendBinding
import com.lee989898.todayeat.src.menu.MenuActivity
import com.lee989898.todayeat.src.survey.surveyActivity


class RecommendFragment : Fragment() {

    private lateinit var binding: FragmentRecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendBinding.inflate(inflater, container, false)

        binding.recommendStartIv.setOnClickListener {
            startActivity(Intent(activity, surveyActivity::class.java))
        }
//        binding.recommendStartIv.setOnClickListener {
//            activity?.let{
//                val intent = Intent(context, JoinAllergyActivity::class.java)
//                startActivity(intent)
//            }
//        }


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