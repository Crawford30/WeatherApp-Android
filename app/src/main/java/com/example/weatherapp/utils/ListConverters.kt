package com.example.weatherapp.utils

import androidx.room.TypeConverter
import com.example.weatherapp.models.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListConverters {
    @TypeConverter
    fun toInsuredVehicle(json: String): List<Weather> {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter

    fun toJson(torrent: List<Weather>): String {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().toJson(torrent, type)
    }
}