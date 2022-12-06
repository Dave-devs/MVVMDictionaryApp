package com.example.mvvmdictionaryapp.feature_dictionary.domain.repository

import com.example.mvvmdictionaryapp.core.util.Resource
import com.example.mvvmdictionaryapp.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    //We use Flow to emit multiple values of words for a period of time
    // so we can first emit our loading status and we can get our actual
    // WordInfo from cache, then make a request. In response we emit another
    // list of WordInfo that we actually get from the API and so on...
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}