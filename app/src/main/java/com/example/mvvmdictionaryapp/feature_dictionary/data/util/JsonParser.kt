package com.example.mvvmdictionaryapp.feature_dictionary.data.util

import java.lang.reflect.Type

interface JsonParser {

    //Json Parser for type converter for unrecognized value passed by entity table.
    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(obj: T, type: Type): String?
}
