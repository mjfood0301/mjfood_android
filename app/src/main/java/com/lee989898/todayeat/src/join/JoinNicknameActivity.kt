package com.lee989898.todayeat.src.join

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityJoinNicknameBinding

class JoinNicknameActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinNicknameBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = Application.joinSharedPreferences

        val ageList = Array(100) { i -> i + 1923 }
        val monthList = Array(12) { i -> i + 1 }
        val dayList = Array(31) { i -> i + 1 }

        birthdaySelect(ageList, monthList, dayList)

        binding.joinFinishColorIv.setOnClickListener {
            sharedPreferences.edit().putString("닉네임", binding.joinNicknameEt.text.toString()).commit()
            startActivity(Intent(this, JoinAllergyActivity::class.java))
        }

        binding.joinNicknameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val year = sharedPreferences.getString("년", "")
                val month = sharedPreferences.getString("월", "")
                val day = sharedPreferences.getString("일", "")

                if (binding.joinNicknameEt.length() < 3 ||
                    year == "" ||
                    month == "" ||
                    day == ""
                ) {
                    binding.joinFinishNoColorIv.visibility = View.VISIBLE
                    binding.joinFinishColorIv.visibility = View.GONE
                } else if (binding.joinNicknameEt.length() > 2 &&
                    year != "" && month != "" && day != ""
                ) {
                    binding.joinFinishNoColorIv.visibility = View.GONE
                    binding.joinFinishColorIv.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


    }


    private fun birthdaySelect(
        ageList: Array<Int>,
        monthList: Array<Int>,
        dayList: Array<Int>
    ) {

        binding.joinBirthdayYearSp.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageList)
        binding.joinBirthdayMonthSp.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, monthList)
        binding.joinBirthdayDaySp.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, dayList)

        binding.joinBirthdayYearSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    Log.d("LEE", "${position + 1923}")
                    sharedPreferences.edit().putString("년", "${position + 1923}").commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.joinBirthdayMonthSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    Log.d("LEE", "${position + 1}")
                    sharedPreferences.edit().putString("월", "${position + 1}").commit()

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.joinBirthdayDaySp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    Log.d("LEE", "${position + 1}")
                    sharedPreferences.edit().putString("일", "${position + 1}").commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }
}