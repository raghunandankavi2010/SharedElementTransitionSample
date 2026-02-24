package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapp.navigation.Destination
import com.example.myapp.navigation.TopLevelDestination
import com.example.myapp.ui.components.AppScaffold
import com.example.myapp.ui.screens.DetailScreen
import com.example.myapp.ui.screens.FavoritesScreen
import com.example.myapp.ui.screens.HomeScreen
import com.example.myapp.ui.screens.SettingsScreen
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()

    SharedTransitionLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Destination.MainTabs,
            modifier = Modifier.fillMaxSize(),
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
            composable<Destination.MainTabs> {
                MainTabsScreen(
                    onNavigateToDetail = { itemId ->
                        navController.navigate(Destination.Detail(itemId))
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }

            composable<Destination.Detail> { backStackEntry ->
                val detail = backStackEntry.toRoute<Destination.Detail>()
                DetailScreen(
                    itemId = detail.itemId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainTabsScreen(
    onNavigateToDetail: (Int) -> Unit,
    sharedTransitionScope: androidx.compose.animation.SharedTransitionScope,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope
) {
    val tabsNavController = rememberNavController()
    val navBackStackEntry by tabsNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AppScaffold(
        currentDestination = currentDestination,
        onNavigateToTopLevel = { destination ->
            navigateToTopLevelDestination(tabsNavController, destination)
        },
        showBars = true
    ) { paddingValues ->
        NavHost(
            navController = tabsNavController,
            startDestination = Destination.Home,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Destination.Home> {
                HomeScreen(
                    onItemClick = onNavigateToDetail,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = animatedVisibilityScope
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
        }
    }
}

private fun navigateToTopLevelDestination(
    navController: NavHostController,
    destination: TopLevelDestination
) {
    val route = when (destination) {
        TopLevelDestination.Home -> Destination.Home
        TopLevelDestination.Favorites -> Destination.Favorites
        TopLevelDestination.Settings -> Destination.Settings
    }

    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}