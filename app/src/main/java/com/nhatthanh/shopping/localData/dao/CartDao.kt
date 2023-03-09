package com.nhatthanh.shopping.localData.dao

import androidx.room.*
import com.nhatthanh.shopping.login.model.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCart(cart: Cart)

    @Query("DELETE FROM table_name WHERE id =:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM cart ORDER BY sumPrice DESC")
    fun getAllCart(): Flow<List<Cart>>
}