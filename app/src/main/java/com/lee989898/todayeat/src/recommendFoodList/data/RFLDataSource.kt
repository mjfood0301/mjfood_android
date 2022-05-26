package com.lee989898.todayeat.src.recommendFoodList.data

import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.recommendFoodList.model.FoodItemListModel

class RFLDataSource {
    fun loadRecommendFood():List<FoodItemListModel>{
        return listOf<FoodItemListModel>(
            FoodItemListModel(R.string.food_lable1,R.drawable.rice_cake1,R.string.food_name1),
            FoodItemListModel(R.string.food_lable2,R.drawable.kimbap_rice,R.string.food_name2),
            FoodItemListModel(R.string.food_lable3,R.drawable.hand_pulled_noodle,R.string.food_name3)
        )
    }
}