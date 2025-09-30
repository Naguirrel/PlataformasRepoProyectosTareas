package com.naguirrel.plataslabs8_muchos.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.CharacterDb
import com.naguirrel.plataslabs8_muchos.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CharactersViewModel : ViewModel() {
    private val db = CharacterDb()

    private val _state = MutableStateFlow(UiState<List<Character>>(isLoading = true))
    val state: StateFlow<UiState<List<Character>>> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = UiState(isLoading = true)
        delay(3_000) // 3 segundos de espera
        val ok = Random.nextInt(1, 11) % 2 == 0
        _state.value = if (ok) {
            UiState(data = db.getAllCharacters())
        } else {
            UiState(hasError = true)
        }
    }
}
