package com.naguirrel.plataslabs8_muchos.ui.common

data class UiState<T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val hasError: Boolean = false
)