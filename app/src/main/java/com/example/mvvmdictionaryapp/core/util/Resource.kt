package com.example.mvvmdictionaryapp.core.util

//Sealed class to depict the state of our data emission to UI and emit the state it is at the moment.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}
