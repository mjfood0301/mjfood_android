package com.lee989898.todayeat.src.recommend

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.R


class RecommendWorldCup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend_world_cup)


        //레이아웃 컴포넌트
        val layouts: WorldCupLayout = WorldCupLayout(
            findViewById<TextView>(R.id.world_cup_status),
            findViewById<ImageView>(R.id.selection_1),
            findViewById<ImageView>(R.id.selection_2)
        )

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

        //WorldCupExecutor 실행
        val worldCup = WorldCupExecutor(food_list, layouts)
        worldCup.execute()
    }
}