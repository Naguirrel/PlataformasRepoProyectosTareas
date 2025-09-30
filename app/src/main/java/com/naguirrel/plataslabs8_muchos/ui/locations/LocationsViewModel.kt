package com.naguirrel.plataslabs8_muchos.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.Location
import com.naguirrel.plataslabs8_muchos.data.LocationDb
import com.naguirrel.plataslabs8_muchos.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class LocationsViewModel : ViewModel() {
    private val db = LocationDb()

    private val _state = MutableStateFlow(UiState<List<Location>>(isLoading = true))
    val state: StateFlow<UiState<List<Location>>> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = UiState(isLoading = true)
        delay(4_000) // 4s
        val ok = Random.nextInt(1, 11) % 2 == 0
        _state.value = if (ok) {
            UiState(data = db.getAllLocations())
        } else {
            UiState(hasError = true)
        }
    }
}
