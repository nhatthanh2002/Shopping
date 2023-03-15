package com.nhatthanh.shopping.localData

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nhatthanh.shopping.R
import com.nhatthanh.shopping.Utils
import com.nhatthanh.shopping.localData.dao.CartDao
import com.nhatthanh.shopping.localData.dao.UserDao
import com.nhatthanh.shopping.product.model.Cart
import com.nhatthanh.shopping.login.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Cart::class],
    version = 2,
    exportSchema = true
)

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
                )
                    .build()
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
                quantityItem = 8,
                sumPrice = 888.88,
                imageCart = "https://i.imgur.com/CDljAig.png",
                nameCart = "Imac 27 Inch 5k",
                checkCart = false
            )
            cartDao.insertCart(car)
        }
    }
}

