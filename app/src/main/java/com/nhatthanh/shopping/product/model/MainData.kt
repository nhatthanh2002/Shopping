package com.nhatthanh.shopping.product.model

data class MainData(
    val type: Int=0,
    val listNotifications: List<Notifications> = mutableListOf(),
    val listSeeAll: List<SeeAll> = mutableListOf(),
    val listProducts: List<Products> = mutableListOf()
)