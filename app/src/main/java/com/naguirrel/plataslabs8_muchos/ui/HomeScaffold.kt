package com.naguirrel.plataslabs8_muchos.ui

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.naguirrel.plataslabs8_muchos.nav.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")

@Composable
fun HomeScaffold() {
    val innerNav = rememberNavController()
    val entry by innerNav.currentBackStackEntryAsState()
    val dest = entry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = dest?.hierarchy?.any { it.hasRoute(CharactersRoute::class) } == true,
                    onClick  = { innerNav.navigate(CharactersRoute) { launchSingleTop = true } },
                    icon    = { Icon(Icons.Default.Person, null) },
                    label   = { Text("Characters") }
                )
                NavigationBarItem(
                    selected = dest?.hierarchy?.any { it.hasRoute(LocationsRoute::class) } == true,
                    onClick  = { innerNav.navigate(LocationsRoute) { launchSingleTop = true } },
                    icon    = { Icon(Icons.Default.Place, null) },
                    label   = { Text("Locations") }
                )
                NavigationBarItem(
                    selected = dest?.hierarchy?.any { it.hasRoute(ProfileRoute::class) } == true,
                    onClick  = { innerNav.navigate(ProfileRoute) { launchSingleTop = true } },
                    icon    = { Icon(Icons.Default.AccountCircle, null) },
                    label   = { Text("Profile") }
                )
            }
        }
    ) {
        HomeInnerNav(innerNav)
    }
}

