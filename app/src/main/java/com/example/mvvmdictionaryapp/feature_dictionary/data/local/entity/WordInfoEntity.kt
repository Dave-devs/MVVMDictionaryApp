package com.example.mvvmdictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmdictionaryapp.core.util.Constant.WORD_INFO_ENTITY
import com.example.mvvmdictionaryapp.feature_dictionary.domain.model.Meaning
import com.example.mvvmdictionaryapp.feature_dictionary.domain.model.WordInfo

@Entity( tableName = WORD_INFO_ENTITY)
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val sourceUrls: String,
    val phonetic: String,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            sourceUrls = sourceUrls,
            word = word

        )
    }
}
