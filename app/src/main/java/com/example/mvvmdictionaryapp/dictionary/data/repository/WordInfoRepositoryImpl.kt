package com.example.mvvmdictionaryapp.dictionary.data.repository

import com.example.mvvmdictionaryapp.core.util.Resource
import com.example.mvvmdictionaryapp.dictionary.data.local.WordInfoDao
import com.example.mvvmdictionaryapp.dictionary.data.remote.DictionaryApi
import com.example.mvvmdictionaryapp.dictionary.domain.model.WordInfo
import com.example.mvvmdictionaryapp.dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

//Where caching logic belong(Single source of truth)
class WordInfoRepositoryImpl(
    private val dao: WordInfoDao,
    private val api: DictionaryApi
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        //When we first request word it will emit Loading(See a ProgressBar).
        emit(Resource.Loading())

        //WE get Data from our Database so we can then show Word information if we have it in our cache.
        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        //We now emit our cache word to the Loading state of our UI.
        emit(Resource.Loading(data = wordInfos))

        //Try-Catch block to catch any error from the above block of code before it crash our app with issues like;
        //Poor Internet Connections, Parsing Issue(JsonParser - GsonParser)
        try {
            //we get WordInfo from our remote Api.
            val remoteWordInfos = api.getWordInfo(word)
            //If getWord is successful we could want to delete the word.
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            //We could want to insert new words into our Database
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach the server,poor internet connection!",
                data = wordInfos
            ))
        }
        //If there is no error do this emit to UI
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}