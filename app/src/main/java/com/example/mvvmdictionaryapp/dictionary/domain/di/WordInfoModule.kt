package com.example.mvvmdictionaryapp.dictionary.domain.di

import android.app.Application
import androidx.room.Room
import com.example.mvvmdictionaryapp.core.util.Constant
import com.example.mvvmdictionaryapp.dictionary.data.local.Converters
import com.example.mvvmdictionaryapp.dictionary.data.local.WordInfoDatabase
import com.example.mvvmdictionaryapp.dictionary.data.remote.DictionaryApi
import com.example.mvvmdictionaryapp.dictionary.data.repository.WordInfoRepositoryImpl
import com.example.mvvmdictionaryapp.dictionary.data.util.GsonParser
import com.example.mvvmdictionaryapp.dictionary.domain.repository.WordInfoRepository
import com.example.mvvmdictionaryapp.dictionary.domain.use_cases.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    //We need to provide DictionaryApi that return baseUrl()
    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }

    //We need to provide repository(dao, api) that return repositoryImpl
    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ):WordInfoRepository {
        return WordInfoRepositoryImpl(db.dao, api)
    }

    //We need to provide Database(app) that return Room.databaseBuilder()
    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_database"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    //We need to provide use_case where it return a repository
    @Provides
    @Singleton
    fun provideGetWordUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }
}