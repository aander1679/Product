package com.codeanderdev.product.data.remote

import com.codeanderdev.product.data.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("search")
    fun searchByName(
        @Query("query") textQuery: String,
        @Query("number") number: Int = 50,
        @Query("apiKey") apiToken: String = "71e288ac070d4c26bdabdda1d7220931"
    ): Call<ProductResponse>

}