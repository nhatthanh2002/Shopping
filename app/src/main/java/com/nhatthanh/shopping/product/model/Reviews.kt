package com.nhatthanh.shopping.product.model

data class Reviews(
    val content: String,
    val id: Int,
    val rate: Int,
    val time: String,
    val user_name: String
)