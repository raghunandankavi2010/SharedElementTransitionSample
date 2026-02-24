package com.example.myapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapp.ui.screens.DetailScreen
import com.example.myapp.ui.screens.FavoritesScreen
import com.example.myapp.ui.screens.HomeScreen
import com.example.myapp.ui.screens.SettingsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    sharedTransitionScope: SharedTransitionScope? = null,
    onNavigateToDetail: (Int) -> Unit = { itemId ->
        navController.navigate(Destination.Detail(itemId))
    }
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home,
        modifier = modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) +
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(300)
                    )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300)) +
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(300)
                    )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300)) +
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(300)
                    )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300)) +
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(300)
                    )
        }
    ) {
        composable<Destination.Home> {
            HomeScreen(
                onItemClick = onNavigateToDetail,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable
            )
        }

        composable<Destination.Favorites> {
            FavoritesScreen(
                onItemClick = onNavigateToDetail
            )
        }

        composable<Destination.Settings> {
            SettingsScreen()
        }

        composable<Destination.Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Destination.Detail>()
            DetailScreen(
                itemId = detail.itemId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable
            )
        }
    }
}