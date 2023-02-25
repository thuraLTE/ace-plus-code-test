package com.example.codetest.model

interface Mapper<ProductApiResponse, Product> {

    fun toDomainModel(productApiResponse: ProductApiResponse): Product
}