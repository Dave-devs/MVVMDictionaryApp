package com.example.mvvmdictionaryapp.dictionary.presentation

//UiEvent class to ShowSnackBar pop up.
sealed class UiEvent {
    data class ShowSnackBar(val message: String): UiEvent()
}
