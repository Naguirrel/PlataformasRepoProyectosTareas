package com.naguirrel.plataslabs8_muchos.ui.splash

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SplashScreen(
    toHome: () -> Unit,
    toLogin: () -> Unit,
    vm: SplashViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        vm.decide(onLogged = toHome, onNotLogged = toLogin)
    }
}
