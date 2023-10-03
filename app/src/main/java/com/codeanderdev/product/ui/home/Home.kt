package com.codeanderdev.product.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeanderdev.product.R
import com.codeanderdev.product.ui.navigation.AppScreens


@Composable
fun Home(navController: NavController) {
    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxSize()
            ,
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            HeaderImage(Modifier.align((Alignment.CenterHorizontally)).height(500. dp).padding(30.dp).size(600.dp))
            Spacer(modifier = Modifier.padding(40.dp))

            Row(modifier = Modifier.fillMaxWidth().background(color = Color.Blue).padding(16.dp).align((Alignment.CenterHorizontally)),
                horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = { navController.navigate(route = AppScreens.ProductSearch.route) },
                    modifier = Modifier.padding(bottom = 8.dp)// Espacio entre el botón y el segundo botón

                ) {
                    Text(text = "Find Product")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { },modifier = Modifier.padding(bottom = 8.dp)

                ) {
                    Text(text = "Favourite Product")
                }
            }
        }
    }
}

@Composable

fun HeaderImage(modifier: Modifier){
    Image(painter = painterResource(id = R.drawable.branding) ,
        contentDescription = "Header",modifier= modifier)
}