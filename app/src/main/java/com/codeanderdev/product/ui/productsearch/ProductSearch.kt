package com.codeanderdev.product.ui.productsearch

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.codeanderdev.product.data.local.AppDataBase
import com.codeanderdev.product.data.model.Product
import com.codeanderdev.product.repository.ProductRepository
import com.codeanderdev.product.ui.productlist.ProductList
import com.codeanderdev.product.utils.Result

@Composable
fun Search( selectProduct:(Int)->Unit){
    val textQuery = remember { mutableStateOf("") }
    val product = remember { mutableStateOf(listOf<Product>()) }
    val context = LocalContext.current

    Column(modifier= Modifier.fillMaxWidth()) {
        ProductSearch(textQuery,product)
        ProductList(product, selectProduct)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearch(textQuery: MutableState<String>,
               products: MutableState<List<Product>>,

               ) {

    val context = LocalContext.current
    val productDao = AppDataBase.getInstance(context).productDao()

    val repository = ProductRepository(productDao)
    OutlinedTextField(modifier = Modifier.fillMaxWidth(), value = textQuery.value, onValueChange ={ newTextQuery ->
        textQuery.value = newTextQuery

    },
        leadingIcon = {
            Icon(Icons.Filled.Search,null)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),

        keyboardActions = KeyboardActions(
            onSearch ={
                repository.searchByName(textQuery.value){ result ->
                    if(result is Result.Success){
                        products.value = (result.data)!!

                    } else{
                        Toast.makeText(context, result.message!!, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        )




    )

}