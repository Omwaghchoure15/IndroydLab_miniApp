package com.example.indroydlab

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.indroydlab.cart.CartManager
import com.example.indroydlab.screen.CartScreen
import com.example.indroydlab.screen.ProductScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val cartManager = CartManager()

    NavHost(
        navController = navController,
        startDestination = Routes.PRODUCT
    ) {
        composable(Routes.PRODUCT) {
            ProductScreen(
                cartManager = cartManager,
                onCartClick = {
                    navController.navigate(Routes.CART)
                }
            )
        }

        composable(Routes.CART) {
            CartScreen(cartManager = cartManager)
        }
    }
}
