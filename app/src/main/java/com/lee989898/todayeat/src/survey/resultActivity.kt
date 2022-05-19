package com.lee989898.todayeat.src.survey

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import com.lee989898.todayeat.R

class resultActivity : AppCompatActivity(){
    var result = arrayListOf<String>()
    //var food_list = arrayListOf<String>()
    //var food_list = arrayListOf<FoodData>()
    var list_name = arrayListOf<String>()
    var list_img = arrayListOf<Int>()
    var random = Random(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var img = findViewById<ImageView>(R.id.img)
        var food_text = findViewById<TextView>(R.id.food)
        var list_btn = findViewById<Button>(R.id.list_button)
        var around_btn = findViewById<Button>(R.id.around_button)

        img.clipToOutline = true
        result = intent.getStringArrayListExtra("result") as ArrayList<String>

        getList()
        setFood(food_text, img)

        list_btn.setOnClickListener{ view ->
            goList()
        }
    }

    fun goList(){
        var intent = Intent(this, listActivity::class.java)
        //intent.putExtra("food_list", food_list)
        intent.putExtra("name", list_name)
        intent.putExtra("img", list_img)
        startActivity(intent)
    }

    fun setFood(text : TextView, img : ImageView){
        //var randomNumber = random.nextInt(food_list.size)
        //var food = food_list.get(randomNumber)
        //var f_text = food.getName() + "!"
        //var f_img = food.getImg()

        //text.setText(f_text)
        //img.setImageResource(f_img)

        var randomNum = random.nextInt(list_name.size)

        text.setText(list_name.get(randomNum))
        img.setImageResource(list_img.get(randomNum))
    }

    fun getList(){
        /*
        var food = FoodData("떡볶이", R.drawable.sample_1)
        food_list.add(food)
        food = FoodData("피자", R.drawable.sample_2)
        food_list.add(food)
        food = FoodData("족발", R.drawable.sample_3)
        food_list.add(food)
        food = FoodData("매운탕", R.drawable.sample_4)
        food_list.add(food)
        food = FoodData("고등어 구이", R.drawable.sample_5)
        food_list.add(food)
        food = FoodData("초밥", R.drawable.sample_6)
        food_list.add(food)
        */

        list_name.add("떡볶이")
        list_name.add("피자")
        list_name.add("족발")
        list_name.add("매운탕")
        list_name.add("고등어 구이")
        list_name.add("초밥")

        list_img.add(R.drawable.sample_1)
        list_img.add(R.drawable.sample_2)
        list_img.add(R.drawable.sample_3)
        list_img.add(R.drawable.sample_4)
        list_img.add(R.drawable.sample_5)
        list_img.add(R.drawable.sample_6)
    }
}