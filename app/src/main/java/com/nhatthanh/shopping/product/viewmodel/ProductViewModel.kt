package com.nhatthanh.shopping.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.product.model.Color
import com.nhatthanh.shopping.product.model.Notifications
import com.nhatthanh.shopping.product.model.Product
import com.nhatthanh.shopping.product.model.SeeAll

class ProductViewModel : ViewModel() {
    private val _listProduct = MutableLiveData<List<Product>>()
    val listProduct: LiveData<List<Product>>
        get() = getListProduct()
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> = _position

    fun setPosition(position: Int) {
        _position.value = position
    }

    private val _listNotification = MutableLiveData<List<Notifications>>()
    val listNotifications: LiveData<List<Notifications>>
        get() = getListNotifications()

    private val _listSeeAll = MutableLiveData<List<SeeAll>>()
    val listSeeAll: LiveData<List<SeeAll>>
        get() = getListSeeAll()

    private fun getListSeeAll(): MutableLiveData<List<SeeAll>> {
        _listSeeAll.value = mutableListOf(
            SeeAll("New product", "See all")
        )
        return _listSeeAll
    }

    private fun getListProduct(): MutableLiveData<List<Product>> {

        _listProduct.value = mutableListOf(
            Product(
                image = R.drawable.img_product,
                nameProduct = "Imac 27 Inch 5k",
                brandStore = "Apple ",
                rate = 5.0,
                price = 888.88,
                promotion = 1.1,
                listColor = mutableListOf(
                    Color("Black"),
                    Color("Silver"),
                    Color("Green"),
                    Color("Blue")
                )
            ),
            Product(
                image = R.drawable.img_product,
                nameProduct = "Imac 27 Inch 5k ",
                brandStore = "Apple",
                rate = 5.0,
                price = 888.88,
                promotion = 1.1,
                listColor = mutableListOf(
                    Color("Black"),
                    Color("Silver"),
                    Color("Green"),
                    Color("Blue")
                )
            ),
            Product(
                image = R.drawable.img_product,
                nameProduct = "Imac 27 Inch 5k ",
                brandStore = "Apple",
                rate = 5.0,
                price = 888.88,
                promotion = 1.1,
                listColor = mutableListOf(
                    Color("Black"),
                    Color("Silver"),
                    Color("Green"),
                    Color("Blue")
                )
            ),
            Product(
                image = R.drawable.img_product,
                nameProduct = "Imac 27 Inch 5k ",
                brandStore = "Apple",
                rate = 5.0,
                price = 888.88,
                promotion = 1.1,
                listColor = mutableListOf(
                    Color("Black"),
                    Color("Silver"),
                    Color("Green"),
                    Color("Blue")
                )
            )
        )
        return _listProduct
    }

    private fun getListNotifications(): MutableLiveData<List<Notifications>> {
        _listNotification.value = mutableListOf(
            Notifications(R.drawable.banner),
            Notifications(R.drawable.banner),
            Notifications(R.drawable.banner)
        )
        return _listNotification
    }
}