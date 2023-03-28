package com.nhatthanh.shopping.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "quantityItem")
    var quantityItem: Int = 0,
    @ColumnInfo(name = "sumPrice")
    var sumPrice: Double = 0.0,
    @ColumnInfo(name = "priceCart")
    val nameCart: String = "",
    @ColumnInfo(name = "imageCart")
    val imageCart: String = "",
    @ColumnInfo(name = "checkCart")
    var checkCart: Boolean = false
) : Serializable