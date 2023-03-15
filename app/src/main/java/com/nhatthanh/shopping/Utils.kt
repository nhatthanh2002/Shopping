package com.nhatthanh.shopping

import android.content.Context
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {
        const val closeDrawer = "close_drawer_layout_layout"
        const val KEY_USER = "save_user"
        const val EMAIL_USER = "email_user"
        const val PASSWORD_USER = "password_user"
        const val REMEMBER_USER = "remember_user"
        const val DATABASE_NAME = "DATABASE_SHOPPING"
        const val TYPE_NOTIFICATION = 1
        const val TYPE_SEE_ALL = 2
        const val TYPE_PRODUCT = 3
        const val ID_PRODUCT = "id_product"
        const val DESCRIPTION = "description"
        const val BASE_URL = "https://raw.githubusercontent.com/"
        const val SHOW_DIALOG = "SHOW_DIALOG"

        val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

    }
}