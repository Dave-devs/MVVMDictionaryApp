package com.example.mvvmdictionaryapp.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdictionaryapp.core.util.Constant.DELAY_MILLIS_SEC
import com.example.mvvmdictionaryapp.core.util.Resource
import com.example.mvvmdictionaryapp.feature_dictionary.domain.use_cases.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo //ViewModel call the use-cases directly.
    //from use_cases getWordInfo
): ViewModel() {

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    //Event sharedFlow for our UiEvent
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    //Return a new MutableState initialized in String for our above WordInfoStates(meanings: List<Meaning>,phonetic: String,sourceUrls: List<String>)
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    //Coroutines to cancel initial word typed into onSearch function(TextField).
    private var searchJob: Job? = null

    //Function for everytime we click on search button in our Search TextField.
    fun onSearch(query: String) {
       _searchQuery.value = query
       searchJob?.cancel()
       searchJob = viewModelScope.launch {
       //Delay to check if we type another character into the search field to then call searchJob.cancel() on it.
           delay(DELAY_MILLIS_SEC)
           //If we don't change the search character after the 5 second delay it will run this;
           getWordInfo(query)
               .onEach { result ->
                   when(result) {
                       is Resource.Success -> {
                          _state.value = state.value.copy(
                              wordInfoItems = result.data ?: emptyList(),
                              isLoading = false
                          )
                       }
                       is Resource.Error -> {
                           _state.value = state.value.copy(
                               wordInfoItems = result.data ?: emptyList(),
                               isLoading = true
                           )
                           _eventFlow.emit(UiEvent.ShowSnackbar(
                               result.message ?: "Unknown error"
                           ))
                       }
                       is Resource.Loading -> {
                           _state.value = state.value.copy(
                               wordInfoItems = result.data ?: emptyList(),
                               isLoading = false
                           )
                       }
                   }
               }.launchIn(this)
       }
    }
}