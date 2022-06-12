package com.lee989898.todayeat.src.recommend

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class WorldCupLayout constructor(val status : TextView, val choice1 : ImageView, val choice2 : ImageView) {

    fun setSelect(action: (Int) -> Unit) {
        choice1.setOnClickListener {
            action(0)
        }

        choice1.setOnClickListener {
            action(1)
        }
    }

    fun select(selection : Int?) {
        when (selection) {
            null -> {
                selectAnimate(5F, 5F)
            }
            0 -> {
                selectAnimate(10F, 0F)
            }
            1 -> {
                selectAnimate(0F, 10F)
            }
        }
    }

    private fun selectAnimate(fst_weight: Float, snd_weight: Float) {
        val one_params =  choice1.layoutParams as LinearLayout.LayoutParams
        val two_params =  choice2.layoutParams as LinearLayout.LayoutParams
        one_params.weight = fst_weight
        two_params.weight = snd_weight
        choice1.parent.requestLayout()
    }

}