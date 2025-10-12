package com.naguirrel.plataslabs8_muchos.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.local.seedLocalData
import com.naguirrel.plataslabs8_muchos.data.session.UserPrefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = UserPrefs(app)

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    fun login(name: String, onSuccess: () -> Unit) = viewModelScope.launch {
        _ui.value = LoginUiState(isLoading = true)
        delay(4_000)
        runCatching {
            prefs.setName(name)
            seedLocalData(getApplication())
        }.onSuccess {
            _ui.value = LoginUiState()
            onSuccess()
        }.onFailure {
            _ui.value = LoginUiState(isLoading = false, error = it.message)
        }
    }
}
