package com.nhatthanh.shopping.product.model

data class Product(
    var image: Int=0,
    var nameProduct: String="",
    var brandStore: String="",
    var rate: Double=0.0,
    var price: Double=0.0,
    var promotion: Double=0.0,
    val listColor: List<Color> = mutableListOf()
):java.io.Serializable