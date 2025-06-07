package com.example.reminderapp

import java.time.format.DateTimeFormatter
import androidx.room.TypeConverter
import java.time.LocalDateTime
class Converter {
    private val  formatter= DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime?): String {
        return value?.format(formatter) ?: ""

    }
    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? {
        return value?. let{LocalDateTime.parse(it, formatter)}}
    }


