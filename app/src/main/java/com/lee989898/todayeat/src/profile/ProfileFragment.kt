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
import com.lee989898.todayeat.src.profile.profilemodel.ResponseProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

//        joinNetwork()

        binding.profileDeleteIv.setOnClickListener{
            deleteNetwork()

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

    private fun deleteNetwork(){
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

    private fun joinNetwork() {

        val sharedPreferences = Application.tokenSharedPreferences
        val jwt = sharedPreferences.getString("kakaotoken", "hello")!!
        val id = sharedPreferences.getInt("id", 0)!!

        val call = ServiceCreator.profileService.getProfile(jwt, id)

        call.enqueue(object : Callback<ResponseProfile> {

            override fun onResponse(
                call: Call<ResponseProfile>,
                response: Response<ResponseProfile>
            ) {

                if (response.isSuccessful) {

                } else Toast.makeText(requireContext(), "프로필 불러오기를 실패하셨습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<ResponseProfile>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }
}