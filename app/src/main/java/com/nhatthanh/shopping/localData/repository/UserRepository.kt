package com.nhatthanh.shopping.localData.repository

import androidx.annotation.WorkerThread
import com.nhatthanh.shopping.localData.dao.UserDao
import com.nhatthanh.shopping.login.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val allUser:Flow<List<User>> = userDao.getAllUser()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUser(user: User){
       userDao.insertUser(user)
    }
}