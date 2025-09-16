package com.naguirrel.plataslabs8_muchos.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharacterDetailRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharacterDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersGraph
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersScreen
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationDetailRoute
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationsGraph
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationsRoute
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationsScreen
import com.naguirrel.plataslabs8_muchos.ui.profile.ProfileRoute
import com.naguirrel.plataslabs8_muchos.ui.profile.ProfileScreen

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val nav = rememberNavController()

    val backStackEntry by nav.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination

    val selectedIndex = when {
        destination.isInPackage("ui.characters") -> 0
        destination.isInPackage("ui.locations") -> 1
        destination.isInPackage("ui.profile") -> 2
        else -> 0
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        nav.navigate(CharactersRoute) {
                            popUpTo(nav.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Group, contentDescription = "Characters") },
                    label = { Text("Characters") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        nav.navigate(LocationsRoute) {
                            popUpTo(nav.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Public, contentDescription = "Locations") },
                    label = { Text("Locations") }
                )
                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        nav.navigate(ProfileRoute) {
                            popUpTo(nav.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { inner ->
        NavHost(
            navController = nav,
            startDestination = CharactersGraph,
            modifier = Modifier.padding(inner)
        ) {
            navigation<CharactersGraph>(startDestination = CharactersRoute) {
                composable<CharactersRoute> {
                    CharactersScreen(
                        onCharacterClick = { id -> nav.navigate(CharacterDetailRoute(id)) }
                    )
                }
                composable<CharacterDetailRoute> { entry ->
                    val args = entry.toRoute<CharacterDetailRoute>()
                    CharacterDetailScreen(
                        id = args.id,
                        onBack = { nav.popBackStack() }
                    )
                }
            }
            navigation<LocationsGraph>(startDestination = LocationsRoute) {
                composable<LocationsRoute> {
                    LocationsScreen(
                        onLocationClick = { id -> nav.navigate(LocationDetailRoute(id)) }
                    )
                }
                composable<LocationDetailRoute> { entry ->
                    val args = entry.toRoute<LocationDetailRoute>()
                    LocationDetailScreen(
                        id = args.id,
                        onBack = { nav.popBackStack() }
                    )
                }
            }
            composable<ProfileRoute> {
                ProfileScreen(
                    onLogout = onLogout
                )
            }
        }
    }
}

private fun NavDestination?.isInPackage(suffix: String): Boolean =
    this?.hierarchy?.any { it.route?.contains(suffix) == true } == true
