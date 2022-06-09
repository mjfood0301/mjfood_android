package com.lee989898.todayeat.src.survey

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import kotlin.random.Random
import com.lee989898.todayeat.R

class resultActivity : AppCompatActivity(){
    var result = arrayListOf<String>()
    //var food_list = arrayListOf<String>()
    //var food_list = arrayListOf<FoodData>()
    var list_name = arrayListOf<String>()
    var list_img = arrayListOf<String>()
    var random = Random(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var img = findViewById<ImageView>(R.id.img)
        var food_text = findViewById<TextView>(R.id.food)
        var list_btn = findViewById<Button>(R.id.list_button)
        var around_btn = findViewById<Button>(R.id.around_button)

        img.clipToOutline = true
        list_name = intent.getStringArrayListExtra("food_name") as ArrayList<String>
        list_img = intent.getStringArrayListExtra("food_image") as ArrayList<String>

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
        img.setImageURI(list_img.get(randomNum).toUri())
    }
}