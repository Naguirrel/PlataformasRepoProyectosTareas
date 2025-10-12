package com.naguirrel.plataslabs8_muchos.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naguirrel.plataslabs8_muchos.nav.HomeRoute
import com.naguirrel.plataslabs8_muchos.nav.LoginRoute
import com.naguirrel.plataslabs8_muchos.nav.SplashRoute
import com.naguirrel.plataslabs8_muchos.ui.login.LoginScreen
import com.naguirrel.plataslabs8_muchos.ui.splash.SplashScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = SplashRoute) {

        composable<SplashRoute> {
            SplashScreen(
                toHome = { nav.navigate(HomeRoute) { popUpTo(0) } },
                toLogin = { nav.navigate(LoginRoute) { popUpTo(0) } }
            )
        }

        composable<LoginRoute> {

            LoginScreen(
                onLogged = {
                    nav.navigate(HomeRoute) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable<HomeRoute> {
            HomeScaffold()
        }
    }
}
