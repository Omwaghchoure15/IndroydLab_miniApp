package com.example.indroydlab.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.indroydlab.ui.shared.ProductCard
import com.example.indroydlab.ui.shared.topbar.TopbarApp
import com.example.indroydlab.ui.theme.IndroydLabTheme
import com.example.indroydlab.ui.viewmodel.CartViewModel
import com.example.indroydlab.ui.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    onCartClick: () -> Unit,
    onProductClick: (Int) -> Unit
) {
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    var isDarkTheme by remember { mutableStateOf(false) }
    val products = productViewModel.products

    IndroydLabTheme( darkTheme = isDarkTheme ) {
        Scaffold(
            topBar = {
                TopbarApp(
                    "Testing",
                    onClickBack = {}
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
                            cartViewModel = cartViewModel,
                            onClick ={ onProductClick(products.id)}
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    IndroydLabTheme {
        ProductScreen(
            onCartClick = { },
            onProductClick = { }
        )
    }
}