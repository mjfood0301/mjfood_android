package com.lee989898.todayeat.src.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import com.lee989898.todayeat.R
import kotlin.math.log

class surveyActivity : AppCompatActivity() {
    var step = 0
    var result = arrayOfNulls<String>(5)
    var choose_1 = "음식"
    var choose_2 = "디저트"
    var choose_3 = ""
    var max_step = 1
    var select = ""


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




        Q1.setText(choose_1 + "?")
        Q2.setText(choose_2 + "?")
        A1.setText(choose_1 )
        A2. setText(choose_2 )
        A3.setVisibility(View.INVISIBLE)
        img.setVisibility(View.INVISIBLE)

        next.setOnClickListener{ view ->
            if(step < max_step) {
                if (A1.isChecked) {
                    result[step] = A1.text.toString()
                } else if (A2.isChecked) {
                    result[step] = A2.text.toString()
                } else if(A3.isChecked) {
                    result[step] = A3.text.toString()
                }

                if(result[step] != null) {
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
                Log.v(step.toString(), "step_f")
                Log.v(result[0], "result_0")
                Log.v(result[1], "result_1")
                Log.v(result[2], "result_2")
                Log.v(result[3], "result_3")
                Log.v(result[4], "result_4")
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