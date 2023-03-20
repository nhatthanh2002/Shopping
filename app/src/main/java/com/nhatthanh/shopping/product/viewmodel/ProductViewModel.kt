package com.nhatthanh.shopping.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nhatthanh.shopping.product.model.*

class ProductViewModel : ViewModel() {
    private val _listProduct = MutableLiveData<List<Products>>()
    val listProduct: LiveData<List<Products>>
        get() = _listProduct

    private val _product = MutableLiveData<ProductDetail>()
    val productDetail: LiveData<ProductDetail> = _product

    private val _listNotification = MutableLiveData<List<Notifications>>()
    val listNotifications: LiveData<List<Notifications>>
        get() = _listNotification

    private val _listSeeAll = MutableLiveData<List<SeeAll>>()
    val listSeeAll: LiveData<List<SeeAll>>
        get() = _listSeeAll

    init {
        _listSeeAll.value = mutableListOf(
            SeeAll("New product", "See all")
        )
    }

    fun setProduct(products: ProductDetail) {
        _product.value = products
    }

    fun setListProduct(list: List<Products>) {
        _listProduct.value = list
    }

    fun setListNotifications(list: List<Notifications>) {
        _listNotification.value = list
    }


}