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
        get() = getListSeeAll()

    private val _listReview = MutableLiveData<List<Reviews>>()
    val listReview: LiveData<List<Reviews>>
        get() = _listReview


    private fun getListSeeAll(): MutableLiveData<List<SeeAll>> {
        _listSeeAll.value = mutableListOf(
            SeeAll("New product", "See all")
        )
        return _listSeeAll
    }

    fun setProduct(products: ProductDetail) {
        _product.value = products
    }

    fun getListProduct(list: List<Products>): MutableLiveData<List<Products>> {

        _listProduct.value = list
        return _listProduct
    }

    fun getListNotifications(list: List<Notifications>): MutableLiveData<List<Notifications>> {
        _listNotification.value = list
        return _listNotification
    }

    fun getListReview(list: List<Reviews>): MutableLiveData<List<Reviews>> {
        _listReview.value = list
        return _listReview
    }
}