package com.naguirrel.plataslabs8_muchos.ui.locations

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.LocationDb
import com.naguirrel.plataslabs8_muchos.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class LocationDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val db = LocationDb()
    private val args = savedStateHandle.toRoute<LocationDetailRoute>() // ← SavedStateHandle

    private val _state = MutableStateFlow(UiState<Location>(isLoading = true))
    val state: StateFlow<UiState<Location>> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = UiState(isLoading = true)
        delay(2_000) // 2s
        val ok = Random.nextInt(1, 11) % 2 == 0
        _state.value = if (ok) {
            UiState(data = db.getLocationById(args.id))
        } else {
            UiState(hasError = true)
        }
    }
}
