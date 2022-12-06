package com.example.mvvmdictionaryapp.feature_dictionary.data.remote.dto

import com.example.mvvmdictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: String,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word
        )
    }
}