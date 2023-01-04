package com.example.mvvmdictionaryapp.dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmdictionaryapp.dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo
) {
    Column(modifier = Modifier) {
        Text(
            text = wordInfo.word,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = wordInfo.sourceUrls,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            color = Color.White
        )
        /*wordInfo.sourceUrls.forEach{ sourceUrl->
            Text(text = sourceUrl.length.toString())
        }*/
        wordInfo.meanings.forEach{ meaning ->
            Text(text = meaning.partOfSpeech,
            fontWeight = FontWeight.Bold
            )
            meaning.definitions.forEachIndexed { i , definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}