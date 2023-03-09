package com.nhatthanh.shopping.localData

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhatthanh.shopping.login.model.Cart
import java.lang.reflect.Type

class DataConverter {
    @TypeConverter
    fun fromCountryLangList(cart: Cart?): String? {
        if (cart == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Cart?>() {}.type
        return gson.toJson(cart, type)
    }

    @TypeConverter
    fun toCountryLangList(cart: String?): Cart? {
        if (cart == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<Cart>() {}.type
        return gson.fromJson(cart, type)
    }
}