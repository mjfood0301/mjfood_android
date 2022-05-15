package com.lee989898.todayeat.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lee989898.todayeat.src.main.MainActivity
import com.lee989898.todayeat.ServiceCreator
import com.lee989898.todayeat.config.Application
import com.lee989898.todayeat.databinding.ActivityLoginBinding
import com.lee989898.todayeat.src.join.JoinNicknameActivity
import com.lee989898.todayeat.src.recommend.RecommendWorldCup
import com.lee989898.todayeat.src.login.model.ResponseKakao
import com.lee989898.todayeat.src.join.JoinAllergyActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = Application.tokenSharedPreferences
        val token = sharedPreferences.getString("kakaotoken", "")

        if(token != ""){
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.loginNoLoginIv.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, RecommendWorldCup::class.java))
        }

        binding.loginKakaoLoginIv.setOnClickListener {

            binding.webview.visibility = View.VISIBLE
            binding.webview.apply {
                settings.domStorageEnabled = true
                settings.javaScriptEnabled = true // 자바스크립트 기능을 동작하게 하기
                settings.setSupportMultipleWindows(true)
                // (페이지를 뜨게하는) WebViewClient를 부모로 하는 무명 클래스 정의 및 인스턴스화
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                binding.webview.loadUrl("http://kaydenserver.shop/oauth2/authorization/kakao")
                webViewClient = object : WebViewClient() {
                    // URL접속할 때 호출되는 메소드
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        return super.shouldOverrideUrlLoading(view, request)
                    }

                    // 페이지 접속 마치면 호출되는 메소드
                    override fun onPageFinished(view: WebView?, url: String?) {
                        ++count
                        val sharedPreferences = Application.tokenSharedPreferences
                        val delete = sharedPreferences.getString("delete", "")
                        if (count == 2) {
                            val kakaoList = mutableListOf<String>()
                            val kakaoResponse = view?.title.toString().split("=")
                            for (response in kakaoResponse) {
                                kakaoList.add(response)
                            }
                            val kakaToken = kakaoList[1].split("&")
                            val sharedPreferences = Application.tokenSharedPreferences
                            val editor = sharedPreferences.edit()
                            editor.putInt("id", kakaoList[2].toInt())
                            editor.putString("kakaotoken", kakaToken[0])
                            editor.commit()
                            binding.webview.visibility = View.GONE
                            startActivity(Intent(baseContext, JoinAllergyActivity::class.java))
                        } else if (delete == "delete") {
                            val kakaoList = mutableListOf<String>()
                            val kakaoResponse = view?.title.toString().split("=")
                            for (response in kakaoResponse) {
                                kakaoList.add(response)
                            }
                            val kakaToken = kakaoList[1].split("&")
                            val sharedPreferences = Application.tokenSharedPreferences
                            val editor = sharedPreferences.edit()
                            editor.putInt("id", kakaoList[2].toInt())
                            editor.putString("kakaotoken", kakaToken[0])
                            editor.commit()
                            binding.webview.visibility = View.GONE
                            startActivity(Intent(baseContext, JoinAllergyActivity::class.java))
                        }


                    }
                }
            }
        }


    }

    private fun getKakaoNetwork() {

        val call = ServiceCreator.kakaoService.getKakaoToken()

        call.enqueue(object : Callback<ResponseKakao> {

            override fun onResponse(call: Call<ResponseKakao>, response: Response<ResponseKakao>) {
                if (response.isSuccessful) {
                    Log.d("LEE22", response.body()?.result?.jwt.toString())
                } else {
                    Toast.makeText(this@LoginActivity, "카카오 API 연결에 실패하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseKakao>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }
}