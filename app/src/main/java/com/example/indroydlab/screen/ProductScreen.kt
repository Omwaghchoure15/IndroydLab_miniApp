package com.example.indroydlab.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indroydlab.model.Product
import com.example.indroydlab.Data.ProductRepository
import com.example.indroydlab.cart.CartManager

@Composable
fun ProductScreen(
    cartManager: CartManager,
    onCartClick: () -> Unit
) {
    val products = ProductRepository.getProducts()

    Column {
        TopBar(onCartClick)
        HorizontalDivider()
        LazyColumn(
            contentPadding = PaddingValues(22.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(product, cartManager)
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onCartClick: () -> Unit) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Indroyd Lab",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(onClick = onCartClick) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart,
                    contentDescription = "Cart")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun ProductCard(product: Product, cartManager: CartManager) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                    color = Color(0xFF2E7D32)
                )
            } else {
                Text(text = "₹${product.originalPrice}")
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = "Tax: ${product.taxPercent}%", color = Color(0xFFD32F2F))

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { cartManager.addToCart(product) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
            Spacer(modifier = Modifier.height(10.dp))



            }
        }
    }


