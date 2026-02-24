package com.example.myapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

// High-level destinations for the BottomBar
sealed class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    data object Home : TopLevelDestination(Icons.Filled.Home, Icons.Outlined.Home, "Home")
    data object Favorites : TopLevelDestination(Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder, "Favorites")
    data object Settings : TopLevelDestination(Icons.Filled.Settings, Icons.Outlined.Settings, "Settings")

    companion object {
        // Use a getter to avoid initialization order issues where objects might be null
        val all: List<TopLevelDestination>
            get() = listOf(Home, Favorites, Settings)
    }
}

@Serializable
sealed class Destination {
    // Root container for tabs (with Scaffold)
    @Serializable data object MainTabs : Destination()
    
    // Tab routes
    @Serializable data object Home : Destination()
    @Serializable data object Favorites : Destination()
    @Serializable data object Settings : Destination()

    // Detail route (sibling to MainTabs, no Scaffold)
    @SuppressLint("UnsafeOptInUsageError")
    @Serializable data class Detail(val itemId: Int) : Destination()
}