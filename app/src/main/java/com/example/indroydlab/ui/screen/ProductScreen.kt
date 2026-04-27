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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.ui.shared.ProductCard
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.example.indroydlab.ui.viewmodel.CartViewModel
import com.example.indroydlab.ui.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    ProductviewModel: ProductViewModel,
    CartviewModel: CartViewModel,
    onCartClick: () -> Unit,
    onProductClick: (Int) -> Unit
) {
    var isDarkTheme by remember { mutableStateOf(false) }
    val products = ProductviewModel.products

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
                    items( products, key = {it.id}
                    ){ products ->
                        ProductCard(
                            product = products,
                            viewModel = CartviewModel,
                            onClick = { onProductClick(products.id) }
                        )
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.primary,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = colorScheme.onPrimary,
            actionIconContentColor = Color.Unspecified
        )
    )
}


@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    IndroydLabTheme {
        ProductScreen(
            ProductviewModel = viewModel(),
            CartviewModel = viewModel(),
            onCartClick = {},
            onProductClick = {}
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