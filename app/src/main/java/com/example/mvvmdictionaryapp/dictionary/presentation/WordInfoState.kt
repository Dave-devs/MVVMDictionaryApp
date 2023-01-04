package com.example.mvvmdictionaryapp.dictionary.presentation

import com.example.mvvmdictionaryapp.dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)