package com.example.indroydlab.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.indroydlab.model.Product
import com.example.indroydlab.Data.ProductRepository
import com.example.indroydlab.cart.CartManager
import androidx.compose.ui.tooling.preview.Preview
import com.example.indroydlab.ui.theme.IndroydLabTheme

@Composable
fun ProductScreen(
    cartManager: CartManager,
    onCartClick: () -> Unit
) {
    var isDarkTheme by remember { mutableStateOf(false) }
    val products = ProductRepository.getProducts()

    IndroydLabTheme( darkTheme = isDarkTheme ) {
        Scaffold(
            topBar = {
                TopBar(
                    onCartClick = onCartClick,
                    darkTheme = isDarkTheme,
                    onThemeToggle = { isDarkTheme = it }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                HorizontalDivider()
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.background),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products){
                        products ->
                        ProductCard(product = products, cartManager = cartManager)
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onCartClick: () -> Unit,
    darkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
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
                    contentDescription = "Cart"
                )
            }

            Switch(
                checked = darkTheme,
                onCheckedChange = {onThemeToggle(it)},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.White,
                    uncheckedTrackColor = Color.LightGray,
                    uncheckedThumbColor = Color.White,
                ),
                modifier = Modifier.scale(0.7f),
                thumbContent = {
                    Icon(
                        imageVector = if (darkTheme) Icons.Default.Nightlight else Icons.Default.LightMode,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = if (darkTheme) Color.Black else Color.Gray
                    )
                }
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                    color = colorScheme.secondary,
                )
            } else {
                Text(text = "₹${product.originalPrice}")
            }

            Text(text = "Tax: ${product.taxPercent}%", color = colorScheme.tertiary)

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { cartManager.addToCart(product) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    IndroydLabTheme {
        ProductScreen(
            cartManager = CartManager(),
            onCartClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    IndroydLabTheme {
        TopBar(onCartClick = {}, darkTheme = false, onThemeToggle = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    IndroydLabTheme {
        val sampleProduct = ProductRepository.getProducts()[0]
        ProductCard(product = sampleProduct, cartManager = CartManager())
    }
}