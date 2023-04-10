package com.cinema.classic.common

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    object save : UiEvent()
}