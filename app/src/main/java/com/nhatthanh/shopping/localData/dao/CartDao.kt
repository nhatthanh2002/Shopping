package com.nhatthanh.shopping.localData.dao

import androidx.room.*
import com.nhatthanh.shopping.product.model.Cart
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCart(cart: Cart)

    @Query("DELETE FROM cart WHERE id =:id")
    suspend fun delete(id: Int)

    @Query("UPDATE cart SET quantityItem = :quantity WHERE id =:id")
    suspend fun updateQuantity(id: Int, quantity: Int)

    @Query("SELECT * FROM cart  ORDER BY id DESC")
    fun getAllCart(): Flow<List<Cart>>


}