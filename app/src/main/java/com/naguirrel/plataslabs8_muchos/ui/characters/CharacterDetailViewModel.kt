package com.naguirrel.plataslabs8_muchos.ui.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.CharacterDb
import com.naguirrel.plataslabs8_muchos.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val db = CharacterDb()
    private val args = savedStateHandle.toRoute<CharacterDetailRoute>()

    private val _state = MutableStateFlow(UiState<Character>(isLoading = true))
    val state: StateFlow<UiState<Character>> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = UiState(isLoading = true)
        delay(2_000) // 2 Segundos
        val ok = Random.nextInt(1, 11) % 2 == 0
        _state.value = if (ok) {
            UiState(data = db.getCharacterById(args.id))
        } else {
            UiState(hasError = true)
        }
    }
}
