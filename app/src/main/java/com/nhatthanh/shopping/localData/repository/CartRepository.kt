package com.nhatthanh.shopping.localData.repository

import androidx.annotation.WorkerThread
import com.nhatthanh.shopping.localData.dao.CartDao
import com.nhatthanh.shopping.login.model.Cart
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    val allCart: Flow<List<Cart>> = cartDao.getAllCart()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCart(cart: Cart){
        cartDao.insertCart(cart)
    }
}