package com.codeanderdev.product.ui.productlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.codeanderdev.product.data.local.AppDataBase
import com.codeanderdev.product.data.model.Product
import com.codeanderdev.product.repository.ProductRepository
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductList ( product: MutableState<List<Product>>,
                  selectProduct:(Int)->Unit



){

     val context = LocalContext.current
    val productDao = AppDataBase.getInstance(context).productDao()
    val productRepository = ProductRepository(productDao )
    LazyColumn{
        items(product.value){product ->
            ProductRow(product, selectProduct,
                deleteProduct = {
                productRepository.delete(product)
                    product.isFavorite= false
            },
                insertProduct = {
                    productRepository.save(product)
                    product.isFavorite= false
                }
                )


        }
    }
}

@Composable
fun ProductRow(product: Product,
               selectProduct:(Int)->Unit,
               deleteProduct:()->Unit,
               insertProduct:()->Unit){
    val isFavorite=remember { mutableStateOf(false)
    }
    isFavorite.value=product.isFavorite
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .clickable {
            selectProduct(product.id)
        }){
        Row( verticalAlignment = Alignment.CenterVertically){
            ProductImage(product.image)
            Column(modifier= Modifier.weight(5f)) {
                Text(text = product.title)

            }
            IconButton(modifier = Modifier.weight(1f),onClick = {
                if(isFavorite.value){
                    deleteProduct()

                } else {
                    insertProduct()


                }
                isFavorite.value = !isFavorite.value

            }) {
                Icon(
                    Icons.Default.Favorite,null ,
                tint = if(isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )

            }
        }
    }
}


@Composable
fun ProductImage(url:String){
    GlideImage(
        imageModel = {url},
        imageOptions = ImageOptions(contentScale = ContentScale.Fit),
        modifier = Modifier
            .size(64.dp)
            .clip(shape = RoundedCornerShape(4.dp))
    )
}
