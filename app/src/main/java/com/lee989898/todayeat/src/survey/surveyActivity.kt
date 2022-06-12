package com.lee989898.todayeat.src.survey

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.src.join.JoinNicknameActivity
import com.lee989898.todayeat.src.join.model.ResponseJoin
import com.lee989898.todayeat.src.login.model.ResponseKakao
import com.lee989898.todayeat.src.main.MainActivity
import com.lee989898.todayeat.src.menu.MenuActivity
import com.lee989898.todayeat.src.survey.model.FoodData
import com.lee989898.todayeat.src.survey.model.RecommmendService
import com.lee989898.todayeat.src.survey.model.ResponseRecommend
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.math.log

class surveyActivity : AppCompatActivity() {
    var step = 0
    var result = arrayListOf<String>()
    var choose_1 = "음식"
    var choose_2 = "디저트"
    var choose_3 = ""
    var max_step = 1
    var select = ""


    val foodList = arrayListOf<FoodData>()
//    val names = arrayListOf<String>()
//    val images = arrayListOf<String>()
    val tag_id = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        var Q1 = findViewById<TextView>(R.id.question_1)
        var Q2 = findViewById<TextView>(R.id.question_2)
        var A1 = findViewById<RadioButton>(R.id.answer_1)
        var A2 = findViewById<RadioButton>(R.id.answer_2)
        var A3 = findViewById<RadioButton>(R.id.answer_3)
        var img = findViewById<ImageView>(R.id.img)
        var group = findViewById<RadioGroup>(R.id.radio_group)
        var next = findViewById<ImageButton>(R.id.go_next)
        next.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }



        Q1.setText(choose_1 + "?")
        Q2.setText(choose_2 + "?")
        A1.setText(choose_1 )
        A2. setText(choose_2 )
        A3.setVisibility(View.INVISIBLE)
        img.setVisibility(View.INVISIBLE)

        next.setOnClickListener{ view ->
            if(step < max_step) {
                if (A1.isChecked) {
                    result.add(A1.text.toString())
                } else if (A2.isChecked) {
                    result.add(A2.text.toString())
                } else if(A3.isChecked) {
                    result.add(A3.text.toString())
                }

                if(result.get(step) != null) {
                    isChange()
                    changeText()

                    if (step == 0) {
                        A3.setVisibility(View.VISIBLE)
                        img.setVisibility(View.VISIBLE)
                        A3.setText(choose_3)
                    } else if (step == 1) {
                        A3.setVisibility(View.INVISIBLE)
                        img.setVisibility(View.INVISIBLE)
                    }

                    Q1.setText(choose_1 + "?")
                    Q2.setText(choose_2 + "?")
                    A1.setText(choose_1)
                    A2.setText(choose_2)

                    if (step == 0) {
                        Q2.setText("")
                        Q1.setText(choose_3 + "/  " + choose_1 + "/  " + choose_2)
                    }

                    Log.v(step.toString(), "step")
                    Log.v(max_step.toString(), "max_step")
                    Log.v(result[step], "result")

                    group.clearCheck()
                    addStep()
                }
            }
            if(step == max_step){
                //결과 화면으로
                go_result()
            }
        }
    }

    fun go_result(){
        //invert()

        tag_id.add(19)
        tag_id.add(29)
        tag_id.add(30)

        val sharedPreferences = Application.tokenSharedPreferences
//        val jwt = sharedPreferences.getString("kakaotoken", "hello")!!
        val jwt = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4Ijo0MiwiaWF0IjoxNjU0ODcxMzk0LCJleHAiOjE2NTYzNDI2MjN9.GBzhAEifWbnb35e8XMYq43ImuTgZ0MrZN4Ky9rul2ew"
        val id = sharedPreferences.getInt("id", 0)!!

        val call = ServiceCreator.recommmendService.getRecommend(jwt, tag_id)

        call.enqueue(object : Callback<ResponseRecommend>  {

            override fun onResponse(
                call: Call<ResponseRecommend>,
                response: Response<ResponseRecommend>
            ) {
                if (response.isSuccessful) {
                    try {
                        val data = response.body()?.result
                        val index = data?.lastIndex!!.toLong()
                        for(i in 0..index){
                            foodList.add(data[i.toInt()])
                        }

                        val intent = Intent(this@surveyActivity, resultActivity::class.java)
                        intent.putExtra("food_list", foodList)
                        startActivity(intent)
                        finish()
                    }catch (e : Exception){
                        Log.e("Exception in recommend", e.message.toString())
                    }

                } else {
                    Toast.makeText(applicationContext, "카카오 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseRecommend>, t: Throwable) {
                Log.e("Recommend", "error:$t")
            }
        })
    }

    fun invert(){
        for(str in result){
            when(str){
                "매움" -> tag_id.add(19)
                "안매움" -> tag_id.add(20)
                "밥" -> tag_id.add(21)
                "면" -> tag_id.add(22)
                "빵" -> tag_id.add(23)
                "고기" -> tag_id.add(24)
                "안고기" -> tag_id.add(25)
                "국물 O" -> tag_id.add(26)
                "국물 X" -> tag_id.add(27)
                "차가운 것" -> tag_id.add(28)
                "따듯한 것" -> tag_id.add(29)
                "음식" -> tag_id.add(30)
                "디저트" -> tag_id.add(31)
                "단 것" -> tag_id.add(32)
                "안 단 것" -> tag_id.add(33)
            }
        }
    }

    fun addStep(){
        step++
    }

    fun isChange(){
        if(step == 0 || step == 1){
            select = result[step].toString()
            Log.v(select, "select")
        }

        if(step == 1 && (select.equals("밥") || select.equals("면")))
            max_step = 5
        else if(step == 1 && select.equals("빵"))
            max_step = 3
        else if(step == 0)
            max_step = 4
    }

    fun changeText(){
        if(step == 0){
            if(select.equals("음식")) {
                choose_1 = "밥"
                choose_2 = "면"
                choose_3 = "빵"
            }
            else if(select.equals("디저트")){
                choose_1 = "음료"
                choose_2 = "간식"
                choose_3 = "둘다"
            }
        }
        else if(step >= 1){
            if(select.equals("밥") || select.equals("면")){
                if(step == 1) {
                    choose_1 = "고기"
                    choose_2 = "안고기"
                }
                else if (step == 2) {
                    choose_1 = "국물 O"
                    choose_2 = "국물 X"
                }
                else if (step == 3){
                    choose_1 = "매움"
                    choose_2 = "안매움"
                }
            }
            else if(select.equals("빵")){
                choose_1 = "가볍게"
                choose_2 = "많이"
            }
            else {
                if(step == 1){
                    choose_1 = "차가운 것"
                    choose_2 = "따듯한 것"
                }
                else if(step == 2){
                    choose_1 = "단 것"
                    choose_2 = "안 단 것"
                }
            }
        }
    }
}