package com.lee989898.todayeat.src.recommend

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.survey.model.FoodData


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

        val getList = intent.getStringArrayListExtra("food_list") as ArrayList<FoodData>
        val foodList = mutableListOf<FoodData>()
        foodList.addAll(getList)

        //음식 데이터 불러오기
//        val foodList = mutableListOf<FoodData>(
//            FoodData(1, "@drawable/food_sample_image1" + R.drawable.food_sample_image1, "피자", listOf()),
//        )

        //WorldCupExecutor 실행
        val worldCup = WorldCupExecutor(foodList, layouts)
        worldCup.execute()
    }
}