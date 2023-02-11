package com.example.codetest.model

import com.google.gson.annotations.SerializedName

data class ProductListApiResponse(
    @SerializedName("products") val productList: List<Product>
)
