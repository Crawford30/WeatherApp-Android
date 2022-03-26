package com.example.weatherapp.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current_weather")
data class WeatherModel(
    val base: String,
    @Embedded
    val clouds: Clouds,
    val cod: Double,
    @Embedded
    val coord: Coord,
    val dt: Int,
    @PrimaryKey
    val id: Int,
    @Embedded
    val main: Main,
    val name: String,
    @Embedded
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,


    val weather: List<Weather>,


    @Embedded
    val wind: Wind
)


