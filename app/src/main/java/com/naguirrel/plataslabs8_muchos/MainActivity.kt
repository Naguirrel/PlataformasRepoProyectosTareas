package com.naguirrel.plataslabs8_muchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersScreen
import com.naguirrel.plataslabs8_muchos.ui.detail.CharacterDetailRoute
import com.naguirrel.plataslabs8_muchos.ui.detail.CharacterDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.login.LoginRoute
import com.naguirrel.plataslabs8_muchos.ui.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                val nav = rememberNavController()

                NavHost(
                    navController = nav,
                    startDestination = LoginRoute
                ) {
                    composable<LoginRoute> {
                        LoginScreen(
                            onStart = {
                                nav.navigate(CharactersRoute) {
                                    popUpTo(LoginRoute) { inclusive = true }
                                }
                            },
                            nombreYCarne = "Norman Aguirre - #24479"
                        )
                    }

                    composable<CharactersRoute> {
                        CharactersScreen(
                            onCharacterClick = { id ->
                                nav.navigate(CharacterDetailRoute(id))
                            }
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
            }
        }
    }
}
