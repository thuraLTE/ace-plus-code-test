package com.example.codetest.model

class ProductMapper: Mapper<ProductApiResponse, Product> {

    override fun toDomainModel(productApiResponse: ProductApiResponse): Product {
        return Product(
            title = productApiResponse.title,
            thumbnail = productApiResponse.thumbnail
        )
    }
}