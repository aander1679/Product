package com.codeanderdev.product.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class],version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract  fun productDao(): ProductDao

    companion object{
        fun getInstance(context: Context):AppDataBase{
            val db=
                Room.databaseBuilder(context,AppDataBase::class.java, "product_compose_db")
                    .allowMainThreadQueries()
                    .build()
            return db
        }
    }
}