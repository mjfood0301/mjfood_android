package com.lee989898.todayeat.src.survey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import kotlin.random.Random
import com.lee989898.todayeat.R
import com.lee989898.todayeat.src.recommendFoodList.listActivity
import com.lee989898.todayeat.src.survey.model.FoodData

class resultActivity : AppCompatActivity(){
    var result = arrayListOf<String>()
    var foodList = arrayListOf<FoodData>()
    var random = Random(System.currentTimeMillis())

    lateinit var img_sel : ImageView
    lateinit var text_sel : TextView
    lateinit var btn_list : ImageButton
    lateinit var btn_store : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var img_sel = findViewById<ImageView>(R.id.img)
        var text_sel = findViewById<TextView>(R.id.act_rst_selected_name)
        var btn_list = findViewById<ImageButton>(R.id.act_rst_btn_list)
        var btn_store = findViewById<ImageButton>(R.id.act_rst_btn_store)

        img_sel.clipToOutline = true
        foodList = intent.getStringArrayListExtra("food_list") as ArrayList<FoodData>
        Log.d("data_result", foodList.toString())

        var randomNum = random.nextInt(foodList.size)
        setFood(foodList.get(randomNum))

        btn_list.setOnClickListener{ view ->
            goList()
        }
    }

    fun goList(){
        var intent = Intent(this, listActivity::class.java)
        intent.putExtra("food_list", foodList)
        startActivity(intent)
    }

    fun setFood(data : FoodData){
        //var randomNumber = random.nextInt(food_list.size)
        //var food = food_list.get(randomNumber)
        //var f_text = food.getName() + "!"
        //var f_img = food.getImg()
        //text.setText(f_text)
        //img.setImageResource(f_img)

        text_sel.text = data.name
//        img.setImageURI(list_img.get(randomNum).toUri())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            var recommended = data?.getStringExtra("select_food") as FoodData
            setFood(recommended)
        }
    }
}