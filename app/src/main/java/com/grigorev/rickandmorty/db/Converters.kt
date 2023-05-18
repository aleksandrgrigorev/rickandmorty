package com.grigorev.rickandmorty.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListOfStrings(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toListOfStrings(flatStringList: String): List<String> {
        return flatStringList.split(",")
    }
}