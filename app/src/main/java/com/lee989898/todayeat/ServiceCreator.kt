package com.lee989898.todayeat

import com.google.gson.GsonBuilder
import com.lee989898.todayeat.config.XAccessTokenInterceptor
import com.lee989898.todayeat.src.detail.model.detail.DetailService
import com.lee989898.todayeat.src.detail.model.like.PostLikeService
import com.lee989898.todayeat.src.detail.model.review.ReviewService
import com.lee989898.todayeat.src.detail.model.unlike.PostUnlikeService
import com.lee989898.todayeat.src.fooddetail.model.FoodDetailService
import com.lee989898.todayeat.src.join.model.JoinService
import com.lee989898.todayeat.src.login.model.KakaoService
import com.lee989898.todayeat.src.mylike.model.LikeService
import com.lee989898.todayeat.src.profile.deletemodel.DeleteService
import com.lee989898.todayeat.src.profile.modifymodel.ModifyService
import com.lee989898.todayeat.src.profile.profilemodel.ProfileService
import com.lee989898.todayeat.src.search.model.SearchService
import com.lee989898.todayeat.src.survey.model.RecommmendService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object ServiceCreator {
    private const val BASE_URL = "https://kaydenserver.shop/"

    var gson = GsonBuilder().setLenient().create()

    private fun httpLoggin(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5000, TimeUnit.MILLISECONDS)
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .addInterceptor(httpLoggin()) // API Response 로그 작성용
        .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    
    val kakaoService: KakaoService = retrofit.create(KakaoService::class.java)
    val joinService: JoinService = retrofit.create(JoinService::class.java)
    val profileService: ProfileService = retrofit.create(ProfileService::class.java)
    val deleteService: DeleteService = retrofit.create(DeleteService::class.java)
    val modifyService: ModifyService = retrofit.create(ModifyService::class.java)
    val searchService: SearchService = retrofit.create(SearchService::class.java)
    val detailService: DetailService = retrofit.create(DetailService::class.java)
    val foodDetailService: FoodDetailService = retrofit.create(FoodDetailService::class.java)
    val reviewService: ReviewService = retrofit.create(ReviewService::class.java)
    val likeListService: LikeService = retrofit.create(LikeService::class.java)
    val postLikeService: PostLikeService = retrofit.create(PostLikeService::class.java)
    val postUnlikeService: PostUnlikeService = retrofit.create(PostUnlikeService::class.java)
    val recommmendService: RecommmendService = retrofit.create(RecommmendService::class.java)
}