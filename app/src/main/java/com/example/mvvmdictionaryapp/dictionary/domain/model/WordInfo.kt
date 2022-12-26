package com.example.mvvmdictionaryapp.dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val sourceUrls: String,
    val word: String
)
