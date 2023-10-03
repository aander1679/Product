package com.codeanderdev.product.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="products")
data class ProductEntity (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val title:String,
    @ColumnInfo(name = "image")
    val image:String,
    @ColumnInfo(name = "favorite")
     val favorite: Boolean

)