package com.naguirrel.plataslabs8_muchos.ui.locations

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.RepoProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

data class LocationDetailUiState(
    val isLoading: Boolean = true,
    val data: Location? = null,
    val hasError: Boolean = false
)

class LocationDetailViewModel(
    app: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(app) {

    private val repo = RepoProvider.locations(app)
    private val id: Int = savedStateHandle["id"] ?: error("id required")

    private val _state = MutableStateFlow(LocationDetailUiState())
    val state: StateFlow<LocationDetailUiState> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = LocationDetailUiState(isLoading = true)
        delay(2000)
        val shouldError = (Random.nextInt(1, 11) % 2 != 0)
        if (shouldError) {
            _state.value = LocationDetailUiState(isLoading = false, hasError = true)
            return@launch
        }

        repo.observeById(id).collectLatest { item ->
            _state.value = LocationDetailUiState(
                isLoading = false,
                data = item,
                hasError = item == null
            )
        }
    }
}
