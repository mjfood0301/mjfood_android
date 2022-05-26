package com.lee989898.todayeat.src.recommendResults

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentRecommendResultsBinding


class RecommendResults : Fragment() {
    private lateinit var binding: FragmentRecommendResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendResultsBinding.inflate(inflater,container,false)
        binding.resultsName.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        binding.resultsName.paint.isAntiAlias = true
        binding.recommendListButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_recommendResults_to_recommendFoodList)
        }
        return binding.root
    }


}