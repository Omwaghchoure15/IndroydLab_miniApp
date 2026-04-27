package com.example.indroydlab.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Defines all possible navigation destinations in the app.
 *
 * Extending [NavKey] allows these objects to be used as keys in the Navigation 3 backstack.
 * The [@Serializable] annotation is required for saving/restoring the navigation state
 * (e.g., when the app is rotated or recreated after process death).
 */
@Serializable
sealed interface Routes: NavKey {

    /** The initial landing screen of the app. */
    @Serializable
    data object Home: Routes

    /** Displays a list of available products. */
    @Serializable
    data object ProductList: Routes

    /** Displays items currently in the user's shopping cart. */
    @Serializable
    data object CartItems: Routes, NavKey
    /**
     * Displays details for a specific product.
     *
     * @param ProductId The unique ID of the product to be fetched and displayed.
     * Passing arguments through the constructor provides type-safe navigation.
     */
    @Serializable
    data class ProductDetail(val ProductId: String): Routes, NavKey
}