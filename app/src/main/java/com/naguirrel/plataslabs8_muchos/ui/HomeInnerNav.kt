package com.naguirrel.plataslabs8_muchos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.naguirrel.plataslabs8_muchos.nav.*
import com.naguirrel.plataslabs8_muchos.ui.characters.CharacterDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersScreen
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.locations.LocationsScreen
import com.naguirrel.plataslabs8_muchos.ui.profile.ProfileScreen

@Composable
fun HomeInnerNav(nav: NavHostController) {
    NavHost(navController = nav, startDestination = CharactersRoute) {

        composable<CharactersRoute> {
            CharactersScreen(
                onCharacterClick = { id -> nav.navigate(CharacterDetailRoute(id)) }
            )
        }

        composable<CharacterDetailRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<CharacterDetailRoute>()
            CharacterDetailScreen(id = args.id, onBack = { nav.popBackStack() })
        }

        composable<LocationsRoute> {
            LocationsScreen(
                onLocationClick = { id -> nav.navigate(LocationDetailRoute(id)) }
            )
        }

        composable<LocationDetailRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<LocationDetailRoute>()
            LocationDetailScreen(id = args.id, onBack = { nav.popBackStack() })
        }

        composable<ProfileRoute> {
            ProfileScreen(onLoggedOut = {
                nav.navigate(LoginRoute) { popUpTo(0) }   // logout -> login y sin backstack
            })
        }
    }
}

