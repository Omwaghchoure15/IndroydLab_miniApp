package com.example.indroydlab.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.ui.shared.topbar.TopbarApp
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.example.indroydlab.ui.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(
    productId: String,
    viewModel: ProductViewModel = viewModel()
){
    val product = viewModel.getProductById(productId)

    product?.let {
        Scaffold(
            topBar = {
                TopbarApp(
                    "Product Detail",
                    onClickBack = { }
                )
            }
        ) { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(text = product.name, fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (product.discountedPrice != null) {
                            Text(
                                text = "₹${product.discountedPrice}",
                                color = colorScheme.secondary,
                            )

                            Text(text = "₹${product.originalPrice}")

                        } else {
                            Text(text = "₹${product.originalPrice}")
                        }

                        Text(text = "Tax: ${product.taxPercent}%", color = colorScheme.tertiary)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductDetailPreview( ) {
    IndroydLabTheme{

        ProductDetailScreen(
            productId = String()
        )
    }
}