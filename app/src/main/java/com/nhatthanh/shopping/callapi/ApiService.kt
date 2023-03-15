package com.nhatthanh.shopping.callapi

import androidx.lifecycle.LiveData
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.ProductDetail
import com.nhatthanh.shopping.product.model.Products
import com.nhatthanh.shopping.product.model.Reviews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("longvuntq/ntqtest/main/data/products")
    fun getListProduct(): Call<List<Products>>

    @GET("longvuntq/ntqtest/main/data/banners")
    fun getListNotification(): Call<List<Notifications>>

    @GET("longvuntq/ntqtest/main/data/reviews")
    fun getListReview(): Call<List<Reviews>>

    @GET("longvuntq/ntqtest/main/data/product/{id}")
    fun getProductDetail(@Path("id") id: Int): Call<ProductDetail>

}