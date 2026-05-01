package com.example.indroydlab.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.model.ProductModel
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.example.indroydlab.ui.viewmodel.CartViewModel

@Composable
fun ProductCard(
    product: ProductModel,
    cartViewModel: CartViewModel,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable{ onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = product.name, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(6.dp))

            if (product.isDiscounted) {
                Text(
                    text = "₹${product.originalPrice}",
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Text(
                    text = "₹${product.discountedPrice}",
                    color = colorScheme.secondary,
                )
            } else {
                Text(text = "₹${product.originalPrice}")
            }

            Text(text = "Tax: ${product.taxPercent}%", color = colorScheme.tertiary)

            Spacer(modifier = Modifier.height(10.dp))

            Button(
               onClick = { cartViewModel.addToCart(product) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview(){
    val product = ProductModel(1, " wireless Headphone", 2500.0, 1999.0, true, 18.0)
    IndroydLabTheme{
        ProductCard(
            product = product,
            cartViewModel = viewModel(),
            onClick = { }
        )
    }
}