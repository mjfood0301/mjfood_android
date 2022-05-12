package com.lee989898.todayeat.src.recommend

import android.widget.ImageView
import com.lee989898.todayeat.R

class FoodData {
    private var img_source : Int
    private var name : String

    constructor(food : String, img: Int) {
        name = food
        img_source = img
    }

    public fun getName() : String {
        return name
    }

    public fun getImg() : Int {
        return img_source
    }

    override fun toString() = "FoodData(name=${name})"
}