package com.example.codetest.network

import com.example.codetest.utils.References
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(References.BASE_API_LINK)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }
}