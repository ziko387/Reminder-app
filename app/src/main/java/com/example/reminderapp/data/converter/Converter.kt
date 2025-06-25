package com.example.reminderapp.data.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converter {
    @TypeConverter
    fun fromTimestamp(value: Long?) : LocalDateTime? {
        return value?.let({ Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDateTime() })

    }


    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?) : Long? {
        return date?.atOffset(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    }
    }

