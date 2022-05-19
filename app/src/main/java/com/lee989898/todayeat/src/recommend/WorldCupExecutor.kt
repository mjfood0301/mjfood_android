package com.lee989898.todayeat.src.recommend

import android.widget.ImageView
import android.widget.TextView
import java.util.*

class WorldCupExecutor {
    private lateinit var text_status : TextView
    private lateinit var choice1_view : ImageView
    private lateinit var choice2_view : ImageView
    private lateinit var match_food1 : FoodData
    private lateinit var match_food2 : FoodData
    private lateinit var win_food : FoodData

    private val food_list = mutableListOf<FoodData>()

    private var worldcup_number : Int = 0
    private var rounds : Int = 0
    private var matchs : Int = 0
    private var current_match : Int = 0
    private var round_list : Stack<FoodData> = Stack()
    private var next_round_list : Stack<FoodData> = Stack()

    public fun loadFoods(list : List) {

    }
}