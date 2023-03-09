package com.nhatthanh.shopping.localData.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhatthanh.shopping.login.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM user")
    fun getUser(): User
}