package com.nhatthanh.shopping.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
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
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Cart

        if (id != other.id) {
            return false
        }
        if (quantityItem != other.quantityItem) {
            return false
        }
        if (sumPrice != other.sumPrice) {
            return false
        }
        if (nameCart != other.nameCart) {
            return false
        }
        if (imageCart != other.imageCart) {
            return false
        }
        if (checkCart != other.checkCart) {
            return false
        }



        return true
    }
}