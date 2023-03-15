package com.nhatthanh.shopping.callapi

import com.nhatthanh.shopping.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}