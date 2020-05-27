package com.airbag.dis.disfoot.room.converter

import androidx.room.TypeConverter
import com.airbag.dis.disfoot.model.Shoe
import com.google.gson.Gson

class Converters {
        @TypeConverter
        fun fromJsonString(value: String?): Shoe? {
            return  Gson().fromJson(value,Shoe::class.java)
        }

        @TypeConverter
        fun toJsonString(shoe: Shoe?): String? {
            return Gson().toJson(shoe)
        }

}