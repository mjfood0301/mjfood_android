package com.lee989898.todayeat.src.recommend

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.R
import okhttp3.internal.checkOffsetAndCount
import java.util.*


class RecommendWorldCup : AppCompatActivity() {

    var data : Int = 0
    private lateinit var text_status : TextView
    private lateinit var choice1_view : ImageView
    private lateinit var choice2_view : ImageView
    private lateinit var match_food1 : FoodData
    private lateinit var match_food2 : FoodData
    private lateinit var win_food : FoodData

    private var worldcup_number : Int = 0
    private var rounds : Int = 0
    private var matchs : Int = 0
    private var current_match : Int = 0
    private var round_list : Stack<FoodData> = Stack()
    private var next_round_list : Stack<FoodData> = Stack()

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
            FoodData("food4", R.drawable.food_sample_image4),
            FoodData("food4", R.drawable.food_sample_image1),
            FoodData("food4", R.drawable.food_sample_image2),
            FoodData("food4", R.drawable.food_sample_image3),
            FoodData("food4", R.drawable.food_sample_image4),
        )

            //대진 짜기
        food_list.shuffle()
        round_list.addAll(food_list)
        Log.d("worldcup", round_list.toString())

            //라운드 설정
        worldcup_number = food_list.size
        rounds = food_list.size
        matchs = rounds/2
        current_match = 0
        setMatchStatus()

//        for (i : Int in 1..matchs step(1)) {
//            Log.d("match cycle", "$i")
//        }

            //라운드 세팅
        nextMatch(round_list)

        //라운드 선택
        choice1_view.setOnClickListener {
            select(1)
            next_round_list.add(match_food1)
            Handler().postDelayed({
                select_animate(5F, 5F)
                nextMatch(round_list)
            }, 2000L)
            Log.d("WorldCup", "selection1")
        }

        choice2_view.setOnClickListener {
            select(2)
            next_round_list.add(match_food2)
            Handler().postDelayed({
                select_animate(5F, 5F)
                nextMatch(round_list)
            }, 1000L)
            Log.d("WorldCup", "selection2")
        }
    }

    fun setMatchStatus() {
        text_status.setText("${rounds}강 ${current_match}/${matchs}")
    }

    fun setMatchStatus(rst : String) {
        text_status.setText("${rst} 승리!")
    }

    fun nextRound() {
        rounds = rounds/2
        if (rounds == 1) {
            win_food = next_round_list.pop()
            throw Exception("winner")
        }

        matchs = rounds/2
        current_match = 1
        round_list.clear()
        round_list.addAll(next_round_list)
    }

    fun nextMatch(list : Stack<FoodData>) {
        current_match++
        if (current_match > matchs) {
            try {
                nextRound()
            }
            catch (e : java.lang.Exception) {
                setMatchStatus(win_food.getName())
                return
            }
        }

        match_food1 = list.pop()
        match_food2 = list.pop()
        Log.d("worldcup", list.toString())

        setMatchStatus()
        choice1_view.setImageResource(match_food1.getImg())
        choice2_view.setImageResource(match_food2.getImg())
    }

    fun select(selection : Int) {
        when (selection) {
            1 -> {
                select_animate(10F, 0F)
            }
            2 -> {
                select_animate(0F, 10F)
            }
        }
    }

    fun select_animate(fst_weight: Float, snd_weight: Float) {
        val one_params =  choice1_view.layoutParams as LinearLayout.LayoutParams
        val two_params =  choice2_view.layoutParams as LinearLayout.LayoutParams
        one_params.weight = fst_weight
        two_params.weight = snd_weight
        choice1_view.getParent().requestLayout()
    }

}