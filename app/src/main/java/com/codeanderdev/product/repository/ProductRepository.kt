package com.codeanderdev.product.repository

import com.codeanderdev.product.data.local.ProductDao
import com.codeanderdev.product.data.local.ProductEntity
import com.codeanderdev.product.data.model.Product
import com.codeanderdev.product.data.model.ProductResponse
import com.codeanderdev.product.data.remote.ApiClient
import com.codeanderdev.product.data.remote.ProductService
import com.codeanderdev.product.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository (
    private val productDao: ProductDao,
    private val productService: ProductService = ApiClient.getProductService()


){

    fun searchByName(title:String, callback:(Result<List<Product>>)-> Unit) {
        val searchByName = productService.searchByName(textQuery = title)
        searchByName.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if(response.isSuccessful){
                    try {
                        val products = response.body()!!.product
                        products.forEach{ product->
                            product.isFavorite = productDao.getById(product.id)!=null

                        }


                        callback(Result.Success(response.body()!!.product))
                    }catch (e:Exception){
                        callback(Result.Success(listOf<Product>()))

                    }
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                callback(Result.Error(t.localizedMessage ))
            }
        })

    }


    fun save(product:Product){
        productDao.save(ProductEntity(product.id, product.title, product.image ,product.isFavorite))
           product.isFavorite=true

    }
     fun delete(product:Product){
         productDao.delete(ProductEntity(product.id, product.title, product.image,product.isFavorite))
         product.isFavorite= false


     }

 }



