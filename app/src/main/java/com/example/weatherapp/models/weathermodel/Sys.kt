package com.example.weatherapp.models.weathermodel


import com.google.gson.annotations.SerializedName

data class Sys(
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)