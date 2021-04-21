package com.elena.data.db

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * @author elena
 *         Date: 12/01/2019
 *         Time: 18:32
 */
class AppDbConverters {

    private val gson = GsonBuilder().create()

    @TypeConverter
    fun stringToIdsList(data: String?): List<Int> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun idsListToString(ids: List<Int>): String {
        return gson.toJson(ids)
    }
}