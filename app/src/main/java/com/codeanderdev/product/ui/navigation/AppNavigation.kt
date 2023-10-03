package com.codeanderdev.product.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.codeanderdev.product.ui.home.Home
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeanderdev.product.data.local.ProductEntity
import com.codeanderdev.product.ui.productsearch.Search


@Composable
fun AppNavigation(products: List<ProductEntity>){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.route ) {

        composable(route = AppScreens.Home.route) {
            Home(navController)
        }
        composable("product_search"){
            Search () {id ->
                navController.navigate("${AppScreens.ProductSearch.route}/$id")
            }



        }

        }



}