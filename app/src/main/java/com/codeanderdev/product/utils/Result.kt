package com.codeanderdev.product.utils

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
){
    class Success<T>(data: T): Result<T>(data = data)
    class Error<T>(message: String, data:T?=null):Result<T>(data,message)

}