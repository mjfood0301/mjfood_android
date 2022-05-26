package com.lee989898.todayeat.src.recommendFoodList.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FoodItemListModel (
    @StringRes val stringResourceId:Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val foodNameResourceId:Int,
)