package com.naguirrel.plataslabs8_muchos.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.session.UserPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = UserPrefs(app)

    fun decide(onLogged: () -> Unit, onNotLogged: () -> Unit) {
        viewModelScope.launch {
            val name = prefs.nameFlow.first()
            if (name.isNullOrBlank()) onNotLogged() else onLogged()
        }
    }
}
