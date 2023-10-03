package com.codeanderdev.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import com.codeanderdev.product.data.local.ProductDao
import com.codeanderdev.product.data.local.ProductEntity
import com.codeanderdev.product.ui.navigation.AppNavigation
import com.codeanderdev.product.ui.theme.ProductTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val products = listOf<ProductEntity>()

        setContent {
            ProductTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(products)
                }
            }
        }
    }
}

