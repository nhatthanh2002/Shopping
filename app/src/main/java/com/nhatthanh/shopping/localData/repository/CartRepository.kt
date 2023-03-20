package com.nhatthanh.shopping.localData.repository

import androidx.annotation.WorkerThread
import com.nhatthanh.shopping.localData.dao.CartDao
import com.nhatthanh.shopping.product.model.Cart
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    val allCart: Flow<List<Cart>> = cartDao.getAllCart()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCart(cart: Cart) {
        cartDao.insertCart(cart)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCart(id: Int) {
        cartDao.delete(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateQuantity(id: Int, quantity: Int) {
        cartDao.updateQuantity(id, quantity)
    }

}