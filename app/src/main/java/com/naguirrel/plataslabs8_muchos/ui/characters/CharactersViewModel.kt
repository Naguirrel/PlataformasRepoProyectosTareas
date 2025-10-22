package com.naguirrel.plataslabs8_muchos.ui.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.naguirrel.plataslabs8_muchos.data.Character
import com.naguirrel.plataslabs8_muchos.data.RepoProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

data class CharactersUiState(
    val isLoading: Boolean = false,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)

class CharactersViewModel(app: Application) : AndroidViewModel(app) {

    private val repo = RepoProvider.characters(app)

    private val _state = MutableStateFlow(CharactersUiState(isLoading = true))
    val state: StateFlow<CharactersUiState> = _state

    init { load() }

    fun load() = viewModelScope.launch {
        _state.value = CharactersUiState(isLoading = true)

        // Simula red 4s y decide “éxito/error” como ya tenías
        delay(4000)
        val shouldError = (Random.nextInt(1, 11) % 2 != 0)
        if (shouldError) {
            _state.value = CharactersUiState(isLoading = false, hasError = true)
            return@launch
        }

        repo.ensureSeeded()

        repo.observeAll().collectLatest { list ->
            _state.value = CharactersUiState(
                isLoading = false,
                data = list,
                hasError = false
            )
        }
    }
}
