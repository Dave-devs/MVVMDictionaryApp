package com.example.mvvmdictionaryapp.feature_dictionary.presentation

//UiEvent class to ShowSnackbar pop up.
sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
}
