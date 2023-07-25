package com.example.bookbox.model

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookbox.AppController
import com.example.bookbox.model.product.ProductDao
import com.example.bookbox.model.product.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: DataBase? = null

        fun getInstance(): DataBase? {
            if (INSTANCE == null) {
                synchronized(DataBase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        AppController.getInstance().applicationContext,
                        DataBase::class.java, "book_store.db").allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}