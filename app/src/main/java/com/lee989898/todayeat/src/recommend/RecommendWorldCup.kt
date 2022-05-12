package com.lee989898.todayeat.src.recommend

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.R
import java.util.*


class RecommendWorldCup : AppCompatActivity() {

    var data : Int = 0
    private lateinit var text_status : TextView
    private lateinit var choice1_view : ImageView
    private lateinit var choice2_view : ImageView
    private lateinit var choice1_food : FoodData
    private lateinit var choice2_food : FoodData

    private lateinit var worldcup_number : Int
    private lateinit var rounds : Int
    private lateinit var matchs : Int
    private lateinit var current_match : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_world_cup)

        //레이아웃 컴포넌트
        choice1_view = findViewById<ImageView>(R.id.selection_1)
        choice2_view = findViewById<ImageView>(R.id.selection_2)
        text_status = findViewById<TextView>(R.id.world_cup_status)

        //월드컵 알고리즘
            //음식 데이터 불러오기
        val food_list = mutableListOf<FoodData>(
            FoodData("food1", R.drawable.food_sample_image1),
            FoodData("food2", R.drawable.food_sample_image2),
            FoodData("food3", R.drawable.food_sample_image3),
            FoodData("food4", R.drawable.food_sample_image4)
        )

            //대진 짜기
        food_list.shuffle()
        val match_list : Stack<FoodData> = Stack()
        match_list.addAll(food_list)
        Log.d("worldcup", match_list.toString())

            //라운드 설정
        worldcup_number = food_list.size
        rounds = food_list.size
        matchs = rounds/2
        current_match = 0

//        for (i : Int in 1..matchs step(1)) {
//            Log.d("match cycle", "$i")
//        }

            //라운드 세팅
        nextMatch(match_list)

        //라운드 선택
        choice1_view.setOnClickListener {
            select(1)
            nextMatch(match_list)
            Log.d("WorldCup", "selection1")
        }

        choice2_view.setOnClickListener {
            select(2)
            nextMatch(match_list)
            Log.d("WorldCup", "selection2")
        }
    }

    fun setMatchStatus() {
        text_status.setText("${rounds}강 ${current_match}/${matchs}")
    }

    fun nextMatch(list : Stack<FoodData>) {
        current_match++
        if (current_match <= matchs) {

        }

        choice1_food = list.pop()
        choice2_food = list.pop()
        Log.d("worldcup", list.toString())

        choice1_view.setImageResource(choice1_food.getImg())
        choice2_view.setImageResource(choice2_food.getImg())
    }

    fun select(selection : Int) {
        val one_params =  choice1_view.layoutParams as LinearLayout.LayoutParams
        val two_params =  choice2_view.layoutParams as LinearLayout.LayoutParams

        var sel_one_weight = 0F
        var sel_two_weight = 0F

        when (selection) {
            1 -> {
                sel_one_weight = 10F
                sel_two_weight = 0F
            }
            2 -> {
                sel_one_weight = 0F
                sel_two_weight = 10F
            }
        }
        one_params.weight = sel_one_weight
        two_params.weight = sel_two_weight
        choice1_view.getParent().requestLayout()
    }


}