package com.nhatthanh.shopping.localData.application

import android.app.Application
import com.nhatthanh.shopping.localData.AppDataBase
import com.nhatthanh.shopping.localData.repository.CartRepository
import com.nhatthanh.shopping.localData.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val dataBase by lazy { AppDataBase.getDatabase(this, applicationScope) }
    val cartRepository by lazy { CartRepository(dataBase.cartDao()) }
    val userRepository by lazy { UserRepository(dataBase.userDao()) }
}