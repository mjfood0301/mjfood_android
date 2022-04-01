package com.lee989898.todayeat.src.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.lee989898.todayeat.MainActivity
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityJoinAllergyBinding

class JoinAllergyActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinAllergyBinding
    var allergyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinAllergyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = Application.joinSharedPreferences
        val nickname = sharedPreferences.getString("닉네임", "")
        val year = sharedPreferences.getString("년", "")
        val month = sharedPreferences.getString("월", "")
        val day = sharedPreferences.getString("일", "")

        Log.d("jekkww", "${nickname.toString()}, ${year.toString()}," +
                "${month.toString()}, ${day.toString()} " )


        binding.joinAllergyFinishColorIv.setOnClickListener {
            checkAllergy()
            Log.d("allergy", allergyList.toString())
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun checkAllergy() {

        if(binding.joinAllergyShellfish.isChecked) allergyList.add("1")
        else allergyList.remove("1")
        if(binding.joinAllergyEgg.isChecked) allergyList.add("2")
        else allergyList.remove("2")
        if(binding.joinAllergyWheat.isChecked) allergyList.add("3")
        else allergyList.remove("3")
        if(binding.joinAllergyMilk.isChecked) allergyList.add("4")
        else allergyList.remove("4")
        if(binding.joinAllergyBean.isChecked) allergyList.add("5")
        else allergyList.remove("5")
        if(binding.joinAllergyNut.isChecked) allergyList.add("6")
        else allergyList.remove("6")
        if(binding.joinAllergyPeanut.isChecked) allergyList.add("7")
        else allergyList.remove("7")
        if(binding.joinAllergyFish.isChecked) allergyList.add("8")
        else allergyList.remove("8")
        if(binding.joinAllergyClam.isChecked) allergyList.add("9")
        else allergyList.remove("9")
    }
}