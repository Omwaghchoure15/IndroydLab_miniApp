package com.example.indroydlab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.indroydlab.ui.screen.CartScreen
import com.example.indroydlab.ui.screen.ProductDetailScreen
import com.example.indroydlab.ui.screen.ProductScreen
import com.example.indroydlab.ui.viewmodel.CartViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    // 1. Initialize the backstack with serialization config for custom Routes
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Routes.Home::class)
                    subclass(Routes.ProductScreen::class)
                    subclass(Routes.CartScreen::class)
                    subclass(Routes.ProductDetail::class)
                }
            }
        },
         Routes.Home
    )

    // 2. NavDisplay renders the current screen based on the backstack
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Routes.Home,
                is Routes.ProductScreen -> {
                    NavEntry(key) {
                        ProductScreen(
                            onCartClick = { backStack.add(Routes.CartScreen) },
                            // FIX: Correctly pass the productId from the screen to the route
                            onProductClick = { id ->
                                backStack.add(Routes.ProductDetail(productId = id.toString()))
                            }
                        )
                    }
                }
                is Routes.ProductDetail -> {
                    NavEntry(key) {
                        // key is smart-cast to Routes.ProductDetail here
                        ProductDetailScreen(productId = key.productId)
                    }
                }
                is Routes.CartScreen -> NavEntry(key) {
                    val viewModel: CartViewModel = viewModel()
                    CartScreen(viewModel = viewModel)
                }
                else -> error("Unknown key $key")
            }
        }
    )
}