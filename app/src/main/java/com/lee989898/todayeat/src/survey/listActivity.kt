package com.lee989898.todayeat.src.survey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.R

class listActivity : AppCompatActivity() {
    var list_name = arrayListOf<String>()
    var list_img = arrayListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}