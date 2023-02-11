package com.example.codetest.network

import com.example.codetest.model.ProductListApiResponse
import retrofit2.http.GET

interface NetworkService {

    @GET("products")
    suspend fun fetchAllProducts(): ProductListApiResponse
}