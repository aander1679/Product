package com.codeanderdev.product.ui.navigation

sealed class AppScreens(val route: String){

    object Home : AppScreens("home")
    object ProductList: AppScreens("product_list")
    object ProductSearch: AppScreens("product_search")


}