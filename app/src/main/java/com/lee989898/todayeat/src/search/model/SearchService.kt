package com.lee989898.todayeat.src.search.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("api/foods/")
    fun getSearchResult(@Query("keyword")keyword: String): Call<ResponseSearch>
}