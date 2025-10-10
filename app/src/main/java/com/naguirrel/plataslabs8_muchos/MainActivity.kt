package com.naguirrel.plataslabs8_muchos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.navigation.compose.rememberNavController
import com.naguirrel.plataslabs8_muchos.data.local.DbProvider
import com.naguirrel.plataslabs8_muchos.data.local.seedLocalData
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharacterDetailRoute
import com.naguirrel.plataslabs8_muchos.ui.characters.CharactersScreen
import com.naguirrel.plataslabs8_muchos.ui.characters.CharacterDetailScreen
import com.naguirrel.plataslabs8_muchos.ui.home.HomeRoute
import com.naguirrel.plataslabs8_muchos.ui.home.HomeScreen
import com.naguirrel.plataslabs8_muchos.ui.login.LoginRoute
import com.naguirrel.plataslabs8_muchos.ui.login.LoginScreen
import com.naguirrel.plataslabs8_muchos.ui.theme.RMTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val db = DbProvider.get(applicationContext)
            if (db.characterDao().count() == 0 || db.locationDao().count() == 0) {
                seedLocalData(applicationContext)
            }
        }
        setContent {
            RMTheme {
                val nav = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = nav,
                        startDestination = LoginRoute
                    ) {
                        composable<LoginRoute> {
                            LoginScreen(
                                onStart = {
                                    nav.navigate(HomeRoute) {
                                        popUpTo(LoginRoute) { inclusive = true }
                                    }
                                },
                                nombreYCarne = "Norman Aguirre - 24479"
                            )
                        }
                        composable<HomeRoute> {
                            HomeScreen(
                                onLogout = {
                                    nav.navigate(LoginRoute) {
                                        popUpTo(HomeRoute) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
