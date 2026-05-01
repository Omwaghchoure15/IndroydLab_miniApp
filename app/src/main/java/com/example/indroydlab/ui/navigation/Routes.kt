package com.example.indroydlab.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Defines all possible navigation destinations in the app.
 * Extending [NavKey] allows these objects to be used as keys in the Navigation 3 backstack.
 */
sealed interface Routes : NavKey {

    @Serializable
    data object Home : Routes

    @Serializable
    data object ProductScreen : Routes

    @Serializable
    data object CartScreen : Routes

    /**
     * @param productId using Int to match the ProductModel's ID type.
     */
    @Serializable
    data class ProductDetail(val productId: String) : Routes
}
