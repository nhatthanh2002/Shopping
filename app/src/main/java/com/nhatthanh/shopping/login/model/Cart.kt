package com.nhatthanh.shopping.login.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nhatthanh.shopping.product.model.Product

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "quantityItem")
    var quantityItem: Int = 0,
    @ColumnInfo(name = "sumPrice")
    var sumPrice: Double = 0.0,
    @ColumnInfo(name = "checkItem")
    var checkItem: Boolean = false,
    @ColumnInfo(name = "productItem")
    var productItem: Product
)