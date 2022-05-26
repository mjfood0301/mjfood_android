package com.lee989898.todayeat.src.recommendFoodList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.databinding.FragmentRecommendFoodListBinding
import com.lee989898.todayeat.src.recommendFoodList.adapter.RFLAdapter
import com.lee989898.todayeat.src.recommendFoodList.data.RFLDataSource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RecommendFoodList : Fragment() {
    private lateinit var binding : FragmentRecommendFoodListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendFoodListBinding.inflate(inflater,container,false)
        val myDataset = RFLDataSource().loadRecommendFood()
        binding.recyclerview.adapter = RFLAdapter(this.requireContext(),myDataset)
        binding.cancelButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_recommendFoodList_to_recommendResults)
        }
        return binding.root
    }


}