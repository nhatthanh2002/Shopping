package com.nhatthanh.shopping.localData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.localData.dao.CartDao
import com.nhatthanh.shopping.localData.dao.UserDao
import com.nhatthanh.shopping.login.model.Cart
import com.nhatthanh.shopping.login.model.User
import com.nhatthanh.shopping.product.model.Color
import com.nhatthanh.shopping.product.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [User::class, Cart::class], version = 2, exportSchema = true)

abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao

    companion object {
        private var INSTANCE: AppDataBase? = null


        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Utils.DATABASE_NAME
                ).addCallback(CartDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class CartDatabaseCallback(private val scope: CoroutineScope) :
        Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE.let { database ->
                scope.launch {
                    database?.let { populateDatabase(it.cartDao()) }
                }
            }
        }

        suspend fun populateDatabase(cartDao: CartDao) {
            val car = Cart(
                quantityItem = 8, checkItem = true, productItem =
                Product(
                    image = R.drawable.img_product,
                    nameProduct = "Imac 27 Inch 5k",
                    brandStore = "Apple ",
                    rate = 5.0,
                    price = 888.88,
                    promotion = 1.1,
                    listColor = mutableListOf(
                        Color("Black"),
                        Color("Silver"),
                        Color("Green"),
                        Color("Blue")
                    )
                )
            )
            cartDao.insertCart(car)
        }
    }
}

