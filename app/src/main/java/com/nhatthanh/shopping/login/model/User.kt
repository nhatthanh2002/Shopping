package com.nhatthanh.shopping.login.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @ColumnInfo(name = "fullName")
    var fullName: String = "",
    @PrimaryKey
    @ColumnInfo(name = "email")
    var email: String = "",
    @ColumnInfo(name = "phoneNumber")
    var phoneNumber: String = "",
    @ColumnInfo(name = "password")
    var password: String = ""
) : java.io.Serializable