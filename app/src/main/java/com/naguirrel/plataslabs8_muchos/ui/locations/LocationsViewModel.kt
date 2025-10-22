package com.naguirrel.plataslabs8_muchos.ui.locations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.RepoProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

data class LocationsUiState(
    val isLoading: Boolean = false,
    val data: List<Location>? = null,
    val hasError: Boolean = false
)

class LocationsViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = RepoProvider.locations(app)

    private val _state = MutableStateFlow(LocationsUiState(isLoading = true))
    val state: StateFlow<LocationsUiState> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = LocationsUiState(isLoading = true)
        delay(4000)
        val shouldError = (Random.nextInt(1, 11) % 2 != 0)
        if (shouldError) {
            _state.value = LocationsUiState(isLoading = false, hasError = true)
            return@launch
        }

        repo.ensureSeeded()

        repo.observeAll().collectLatest { list ->
            _state.value = LocationsUiState(
                isLoading = false,
                data = list,
                hasError = false
            )
        }
    }
}
