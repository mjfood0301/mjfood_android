package com.lee989898.todayeat.src.recommend

import android.os.Handler
import android.util.Log
import androidx.core.net.toUri
import com.lee989898.todayeat.src.survey.model.FoodData
import java.util.*

class WorldCupExecutor constructor(val food_list: MutableList<FoodData>, val layouts : WorldCupLayout) {

    //매치 참가자
    val player : ArrayList<FoodData> = arrayListOf(
        FoodData(0, "none", "none", listOf()),
        FoodData(0, "none", "none", listOf()),
    )
    var winner : FoodData? = null

    //대진 정보
    var player_size : Int = 0
    var round : Int = 0
    var matchs : Int = 0

    //대진표
    var current_match : Int = 0
    var round_list : Stack<FoodData> = Stack()
    var next_round_list : Stack<FoodData> = Stack()

    fun execute() {
        setRound()
        setButton()
//        nextRound()
        Log.d("worldcup", round_list.toString())
        nextMatch(true)
    }

    private fun setRound() {
        //대진 설정
        food_list.shuffle()
        round_list.addAll(food_list)
        Log.d("worldcup", round_list.toString())

        //라운드 설정
        player_size = food_list.size
        round = player_size
        matchs = round/2
        current_match = 0
    }
    private fun setButton() {
        val select_action: (Int) -> Unit = {
                sel:Int ->
            layouts.select(sel)
            next_round_list.add(player[sel])
            Handler().postDelayed({
                layouts.select(null)
                nextMatch(false)
            }, 1000L)
            Log.d("WorldCup", "select $sel")
        }
        layouts.setSelect(select_action)
    }

    fun nextRound() {
        //다음 라운드 설정
        round /= 2

        //마지막 라운드 시, 우승자 선출
        if (round == 1) {
            winner = next_round_list.pop()
            win(winner.toString())
            return
        }

        matchs = round/2
        current_match = 0
        round_list.clear()
        round_list.addAll(next_round_list)
    }

    fun nextMatch(init : Boolean) {
        current_match++
//        if (current_match > matchs && !init) {
//            nextRound()
//        }

        player[0] = round_list.pop() as FoodData
        player[1] = round_list.pop() as FoodData
        Log.d("worldcup match",  player[0].toString())
        Log.d("worldcup match",  player[1].toString())

        setStatus()
        layouts.choice1.setImageURI(player[0].image.toUri())
        layouts.choice2.setImageURI(player[1].image.toUri())
    }

    fun setStatus() {
        layouts.status.setText("${round}강 ${current_match}/${matchs}")
    }

    fun win(rst : String) {
        layouts.status.setText("${rst} 승리!")
    }
}