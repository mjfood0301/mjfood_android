package com.lee989898.todayeat.src.recommendFoodList

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.recommend.RecommendWorldCup
import com.lee989898.todayeat.src.survey.adapter.FoodAdapter
import com.lee989898.todayeat.src.survey.model.FoodData
import kotlin.random.Random

class listActivity : AppCompatActivity() {
    var foodList = arrayListOf<FoodData>()
    var list_img = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        foodList = intent.getStringArrayListExtra("food_list") as ArrayList<FoodData>

        val btnRandom = findViewById<ImageButton>(R.id.act_list_btn_random)
        val btnWorldcup = findViewById<ImageButton>(R.id.act_list_btn_worldcup)
        val listRV = findViewById<RecyclerView>(R.id.act_list_content_rv)

        val adapter = FoodAdapter(this, foodList)
        listRV.adapter = adapter
        listRV.layoutManager = LinearLayoutManager(this);

        btnRandom.setOnClickListener {
            selectRandom()
        }

        btnWorldcup.setOnClickListener {
            goWorldcup()
        }

    }

    fun selectRandom() {
        val rand = Random(System.currentTimeMillis())
        val randIdx = rand.nextInt(foodList.size)

        intent.putExtra("select_food", foodList[randIdx])
        setResult(RESULT_OK, intent);
        finish()
    }

    fun goWorldcup() {
        var intent = Intent(this, RecommendWorldCup::class.java)
        intent.putExtra("food_list", foodList)
        startActivity(intent)
    }
}