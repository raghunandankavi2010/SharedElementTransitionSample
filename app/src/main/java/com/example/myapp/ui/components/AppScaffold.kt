package com.example.myapp.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.myapp.navigation.Destination
import com.example.myapp.navigation.TopLevelDestination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    currentDestination: NavDestination?,
    onNavigateToTopLevel: (TopLevelDestination) -> Unit,
    modifier: Modifier = Modifier,
    showBars: Boolean = true,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                currentDestination = currentDestination,
                onNavigateToTopLevel = { destination ->
                    scope.launch { drawerState.close() }
                    onNavigateToTopLevel(destination)
                }
            )
        },
        modifier = modifier,
        gesturesEnabled = showBars
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                if (showBars) {
                    TopAppBar(
                        title = { Text(getScreenTitle(currentDestination)) },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* Share action */ }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share"
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            },
            bottomBar = {
                if (showBars) {
                    BottomNavigationBar(
                        currentDestination = currentDestination,
                        onNavigateToTopLevel = onNavigateToTopLevel
                    )
                }
            }
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}

@Composable
private fun DrawerContent(
    currentDestination: NavDestination?,
    onNavigateToTopLevel: (TopLevelDestination) -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Drawer Header
            Text(
                text = "MyApp",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Explore the world",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // Navigation Items
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = TopLevelDestination.Home.selectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(TopLevelDestination.Home.label) },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Home::class)
                } == true,
                onClick = { onNavigateToTopLevel(TopLevelDestination.Home) }
            )

            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = TopLevelDestination.Favorites.selectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(TopLevelDestination.Favorites.label) },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Favorites::class)
                } == true,
                onClick = { onNavigateToTopLevel(TopLevelDestination.Favorites) }
            )

            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = TopLevelDestination.Settings.selectedIcon,
                        contentDescription = null
                    )
                },
                label = { Text(TopLevelDestination.Settings.label) },
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Settings::class)
                } == true,
                onClick = { onNavigateToTopLevel(TopLevelDestination.Settings) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // Additional Drawer Items
            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Star, contentDescription = null) },
                label = { Text("Rate App") },
                selected = false,
                onClick = { /* Handle rate app */ }
            )

            NavigationDrawerItem(
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                label = { Text("About") },
                selected = false,
                onClick = { /* Handle about */ }
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(
    currentDestination: NavDestination?,
    onNavigateToTopLevel: (TopLevelDestination) -> Unit
) {
    NavigationBar {
        TopLevelDestination.all.forEach { destination ->
            val isSelected = when (destination) {
                TopLevelDestination.Home -> currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Home::class)
                } == true
                TopLevelDestination.Favorites -> currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Favorites::class)
                } == true
                TopLevelDestination.Settings -> currentDestination?.hierarchy?.any {
                    it.hasRoute(Destination.Settings::class)
                } == true
                else -> false
            }

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (isSelected) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        },
                        contentDescription = destination.label
                    )
                },
                label = { Text(destination.label) },
                selected = isSelected,
                onClick = { onNavigateToTopLevel(destination) }
            )
        }
    }
}

private fun getScreenTitle(destination: NavDestination?): String {
    return when {
        destination?.hierarchy?.any { it.hasRoute(Destination.Home::class) } == true -> "Home"
        destination?.hierarchy?.any { it.hasRoute(Destination.Favorites::class) } == true -> "Favorites"
        destination?.hierarchy?.any { it.hasRoute(Destination.Settings::class) } == true -> "Settings"
        else -> "MyApp"
    }
}