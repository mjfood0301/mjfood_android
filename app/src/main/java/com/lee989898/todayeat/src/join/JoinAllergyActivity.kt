package com.lee989898.todayeat.src.join

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lee989898.todayeat.src.main.MainActivity
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityJoinAllergyBinding
import com.lee989898.todayeat.src.join.model.RequestJoin
import com.lee989898.todayeat.src.join.model.ResponseJoin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JoinAllergyActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinAllergyBinding
    private var allergyList = mutableListOf<Int>()
    private var dislikeList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinAllergyBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val sharedPreferences = Application.joinSharedPreferences
//        val nickname = sharedPreferences.getString("닉네임", "")
//        val year = sharedPreferences.getString("년", "")
//        val month = sharedPreferences.getString("월", "")
//        val day = sharedPreferences.getString("일", "")
//
//        Log.d("jekkww", "${nickname.toString()}, ${year.toString()}," +
//                "${month.toString()}, ${day.toString()} " )


        binding.joinAllergyFinishColorIv.setOnClickListener {
            checkAllergy()
//            checkDisLike()
//            Log.d("allergy", allergyList.toString())
//            Log.d("allergy", dislikeList.toString())

            joinNetwork()

        }

    }

    private fun checkAllergy() {

        if (binding.joinAllergyShellfish.isChecked) allergyList.add(1)
        else allergyList.remove(1)
        if (binding.joinAllergyEgg.isChecked) allergyList.add(2)
        else allergyList.remove(2)
        if (binding.joinAllergyWheat.isChecked) allergyList.add(3)
        else allergyList.remove(3)
        if (binding.joinAllergyMilk.isChecked) allergyList.add(4)
        else allergyList.remove(4)
        if (binding.joinAllergyBean.isChecked) allergyList.add(5)
        else allergyList.remove(5)
        if (binding.joinAllergyNut.isChecked) allergyList.add(6)
        else allergyList.remove(6)
        if (binding.joinAllergyPeanut.isChecked) allergyList.add(7)
        else allergyList.remove(7)
        if (binding.joinAllergyFish.isChecked) allergyList.add(8)
        else allergyList.remove(8)
        if (binding.joinAllergyClam.isChecked) allergyList.add(9)
        else allergyList.remove(9)
    }

    private fun checkDisLike() {

        if (binding.joinDislikeCucumber.isChecked) dislikeList.add(1)
        else dislikeList.remove(1)
        if (binding.joinDislikeKimchi.isChecked) dislikeList.add(2)
        else dislikeList.remove(2)
        if (binding.joinDislikeCarrot.isChecked) dislikeList.add(3)
        else dislikeList.remove(3)
        if (binding.joinDislikeBean.isChecked) dislikeList.add(4)
        else dislikeList.remove(4)
        if (binding.joinDislikePimento.isChecked) dislikeList.add(5)
        else dislikeList.remove(5)
        if (binding.joinDislikeEggplant.isChecked) dislikeList.add(6)
        else dislikeList.remove(6)
        if (binding.joinDislikeSpinach.isChecked) dislikeList.add(7)
        else dislikeList.remove(7)
        if (binding.joinDislikePaprika.isChecked) dislikeList.add(8)
        else dislikeList.remove(8)
        if (binding.joinDislikeBroccoli.isChecked) dislikeList.add(9)
        else dislikeList.remove(9)
    }

    private fun joinNetwork() {
        val sharedPreferences = Application.tokenSharedPreferences
        val jwt = sharedPreferences.getString("kakaotoken", "hello")!!
        val id = sharedPreferences.getInt("id", 0)!!


//        var requestJoin = RequestJoin(
//            dislikes = allergyList
//        )

        val call = ServiceCreator.joinService.postJoin(jwt, id, allergyList)

        call.enqueue(object : Callback<ResponseJoin> {

            override fun onResponse(call: Call<ResponseJoin>, response: Response<ResponseJoin>) {

                if (response.isSuccessful) {
                    startActivity(Intent(this@JoinAllergyActivity, MainActivity::class.java))
                } else Toast.makeText(this@JoinAllergyActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseJoin>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }
}