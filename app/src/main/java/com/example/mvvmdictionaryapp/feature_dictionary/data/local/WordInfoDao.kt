package com.example.mvvmdictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmdictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {
    //Creating Data Access Object for the app on how to access the variables in our Entity in Database.

    //Function to insert word from our List of words into WordInfoEntity.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    //Function to delete word from our list of catch list of words.
    @Query("DELETE FROM WORD_INFO_ENTITY WHERE word IN (:word)")
    suspend fun deleteWordInfos(word: List<String>)

    //Function to get word from our catch list of words in WordInfoEntity Database.
    @Query("SELECT * FROM WORD_INFO_ENTITY WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>
}