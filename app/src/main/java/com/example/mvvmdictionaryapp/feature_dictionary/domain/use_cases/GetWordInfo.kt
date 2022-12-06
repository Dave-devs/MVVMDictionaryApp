package com.example.mvvmdictionaryapp.feature_dictionary.domain.use_cases

import com.example.mvvmdictionaryapp.core.util.Resource
import com.example.mvvmdictionaryapp.feature_dictionary.domain.model.WordInfo
import com.example.mvvmdictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//Use_case is a single thing a user can do in your app that's usually
// connected to repository function but doesn't have to, but in this case it is.
class GetWordInfo(
    private val repository: WordInfoRepository
) {
    //If our search field is blank return empty flow of word else return word
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWordInfo(word)
    }
}