package com.codeanderdev.product.data.model

import com.google.gson.annotations.SerializedName

data class ProductResponse (
    @SerializedName("products")
    val product:List<Product>
)

data class Product(
    val id: Int ,
    val title: String,
    val image: String,
    var isFavorite:Boolean
)

