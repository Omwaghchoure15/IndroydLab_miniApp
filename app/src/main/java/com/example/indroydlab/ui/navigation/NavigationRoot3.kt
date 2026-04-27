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
import com.example.indroydlab.ui.screen.ProductScreen
import com.example.indroydlab.ui.viewmodel.CartViewModel
import com.example.indroydlab.ui.viewmodel.ProductViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

/**
 * The main navigation host for the application using Navigation 3.
 * 
 * This composable initializes the backstack, configures state saving for custom routes,
 * and defines how each route maps to a specific screen.
 */
@Composable
fun NavigationRoot( modifier: Modifier = Modifier ){
    // 1. Initialize the backstack. In Navigation 3, the backstack is a mutable list of keys (Routes).
    // It is "remembered" so it survives recompositions and process death.
    val backStack = rememberNavBackStack(
        // 2. Configuration for saving state. Since we use custom classes for Routes, 
        // we must register them for serialization so they can be restored after process death.
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class ) {
                    subclass(Routes.Home::class)
                    subclass(Routes.ProductList::class)
                    subclass(Routes.CartItems::class)
                    subclass(Routes.ProductDetail::class)
                }
            }
        }, Routes.Home // 3. Initial Destination: The starting screen of the app.
    )
    // 4. NavDisplay is the UI host that renders the top-most entry of the backstack.
    NavDisplay(
        modifier = modifier,
        backStack = backStack, // 5. Back handling: simply remove the last item from the list to go back.
        onBack = { backStack.removeLastOrNull() },
        entryDecorators =  listOf (
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        // 6. Entry Provider: Maps the Route keys in your backstack to actual Composable screens.
        entryProvider = { key ->
            when (key) {
                is Routes.Home,
                is Routes.ProductList -> NavEntry(key) {
                    val viewModel: ProductViewModel = viewModel()
                    val cartViewModel: CartViewModel = viewModel()
                    ProductScreen(
                        ProductviewModel = viewModel,
                        CartviewModel = cartViewModel,
                        onCartClick = { backStack.add(Routes.CartItems) },
                        onProductClick = { productId -> backStack.add(Routes.ProductDetail(productId.toString())) }
                    )
                } // Navigate forward by adding a new key to the backstack list.
                is Routes.CartItems -> NavEntry(key) {
                    val viewModel: CartViewModel = viewModel()
                    CartScreen(viewModel = viewModel)
                }
                else -> error("Unknown key $key")

            }
        }
    )
}