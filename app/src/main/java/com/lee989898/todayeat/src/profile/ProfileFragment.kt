package com.lee989898.todayeat.src.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.lee989898.todayeat.R
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.FragmentProfileBinding
import com.lee989898.todayeat.src.join.JoinAllergyActivity
import com.lee989898.todayeat.src.login.LoginActivity
import com.lee989898.todayeat.src.profile.deletemodel.ResponseDelete
import com.lee989898.todayeat.src.profile.modifymodel.ResponseModify
import com.lee989898.todayeat.src.profile.profilemodel.ResponseProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var allergyList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)


        val sharedPreferences = Application.tokenSharedPreferences
        val jwt = sharedPreferences.getString("kakaotoken", "")
        val id = sharedPreferences.getInt("id", 0)


        if(jwt == ""){
            Toast.makeText(activity, "로그인 해야 사용할 수 있는 기능입니다.", Toast.LENGTH_SHORT).show()
        }else{
            profileNetwork(jwt.toString(), id)
        }


        binding.profileDeleteIv.setOnClickListener {
            deleteNetwork()
        }

        binding.profileModifyBeforeIv.setOnClickListener {
            binding.profileAllergyShellfish.isChecked = false
            binding.profileAllergyEgg.isChecked = false
            binding.profileAllergyWheat.isChecked = false
            binding.profileAllergyMilk.isChecked = false
            binding.profileAllergyBean.isChecked = false
            binding.profileAllergyNut.isChecked = false
            binding.profileAllergyPeanut.isChecked = false
            binding.profileAllergyFish.isChecked = false
            binding.profileAllergyClam.isChecked = false
            binding.profileModifyBeforeIv.visibility = View.INVISIBLE
            binding.profileModifyIv.visibility = View.VISIBLE
        }

        binding.profileModifyIv.setOnClickListener {

            binding.profileModifyBeforeIv.visibility = View.VISIBLE
            binding.profileModifyIv.visibility = View.INVISIBLE
            checkAllergy()
            modifyNetwork(jwt.toString(), id)

        }

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.likeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_likeFragment)
        }

        binding.recommendTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_profileFragment_to_recommendFragment)
        }

        return binding.root
    }

    private fun modifyNetwork(jwt: String, id: Int) {

        val call = ServiceCreator.modifyService.patchModify(jwt, id, allergyList)

        call.enqueue(object : Callback<ResponseModify> {

            override fun onResponse(
                call: Call<ResponseModify>,
                response: Response<ResponseModify>
            ) {

                if (response.isSuccessful) {
                    Toast.makeText(requireContext(), "정보 수정를 완료했습니다.", Toast.LENGTH_SHORT)
                        .show()
                } else Toast.makeText(requireContext(), "정보 수정를 실패했습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseModify>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun checkAllergy() {
        if (binding.profileAllergyShellfish.isChecked) allergyList.add(1)
        else allergyList.remove(1)
        if (binding.profileAllergyEgg.isChecked) allergyList.add(2)
        else allergyList.remove(2)
        if (binding.profileAllergyWheat.isChecked) allergyList.add(3)
        else allergyList.remove(3)
        if (binding.profileAllergyMilk.isChecked) allergyList.add(4)
        else allergyList.remove(4)
        if (binding.profileAllergyBean.isChecked) allergyList.add(5)
        else allergyList.remove(5)
        if (binding.profileAllergyNut.isChecked) allergyList.add(6)
        else allergyList.remove(6)
        if (binding.profileAllergyPeanut.isChecked) allergyList.add(7)
        else allergyList.remove(7)
        if (binding.profileAllergyFish.isChecked) allergyList.add(8)
        else allergyList.remove(8)
        if (binding.profileAllergyClam.isChecked) allergyList.add(9)
        else allergyList.remove(9)
    }

    private fun deleteNetwork() {
        val sharedPreferences = Application.tokenSharedPreferences
        val jwt = sharedPreferences.getString("kakaotoken", "hello")!!
        val id = sharedPreferences.getInt("id", 0)!!

        val call = ServiceCreator.deleteService.patchDelete(jwt, id)

        call.enqueue(object : Callback<ResponseDelete> {

            override fun onResponse(
                call: Call<ResponseDelete>,
                response: Response<ResponseDelete>
            ) {

                if (response.isSuccessful) {
                    val sharedPreferences = Application.tokenSharedPreferences
                    val editor = sharedPreferences.edit()
                    editor.putInt("id", 0)
                    editor.putString("kakaotoken", "")
                    editor.putString("delete", "delete")
                    editor.commit()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                } else Toast.makeText(requireContext(), "회원 탈퇴를 실패했습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseDelete>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

    private fun profileNetwork(jwt: String, id: Int) {

        val call = ServiceCreator.profileService.getProfile(jwt, id)

        call.enqueue(object : Callback<ResponseProfile> {

            override fun onResponse(
                call: Call<ResponseProfile>,
                response: Response<ResponseProfile>
            ) {

                if (response.isSuccessful) {
                    val data = response.body()?.result?.userDislikes
                    val index = data?.lastIndex!!.toLong()
                    for(i in 0..index){
                        when(data[i.toInt()].name){
                            "갑각류" -> binding.profileAllergyShellfish.isChecked = true
                            "달걀" -> binding.profileAllergyEgg.isChecked = true
                            "밀" -> binding.profileAllergyWheat.isChecked = true
                            "우유" -> binding.profileAllergyMilk.isChecked = true
                            "콩" -> binding.profileAllergyBean.isChecked = true
                            "견과" -> binding.profileAllergyNut.isChecked = true
                            "땅콩" -> binding.profileAllergyPeanut.isChecked = true
                            "생선" -> binding.profileAllergyFish.isChecked = true
                            else -> binding.profileAllergyClam.isChecked = true
                        }
                    }
                } else Toast.makeText(requireContext(), "프로필 불러오기를 실패하셨습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }
}