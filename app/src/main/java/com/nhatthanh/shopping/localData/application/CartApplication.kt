package com.nhatthanh.shopping.localData.application

import android.app.Application
import com.nhatthanh.shopping.localData.AppDataBase
import com.nhatthanh.shopping.localData.repository.CartRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CartApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val dataBase by lazy { AppDataBase.getDatabase(this, applicationScope) }
    val cartRepository by lazy { CartRepository(dataBase.cartDao()) }
}