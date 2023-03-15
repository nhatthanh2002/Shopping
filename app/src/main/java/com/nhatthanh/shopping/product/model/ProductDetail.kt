package com.nhatthanh.shopping.product.model

data class ProductDetail(
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: Double,
    val sale_price: Double,
    val seller: String,
    val star: Double
)