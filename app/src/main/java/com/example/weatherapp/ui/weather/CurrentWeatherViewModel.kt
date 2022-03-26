package com.example.weatherapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.ApiInterface
import com.example.weatherapp.models.WeatherModel


class CurrentWeatherViewModel() : ViewModel() {
   private val currentWeatherLiveData = MutableLiveData<List<WeatherModel>>()

    private var currentWeatherItems = arrayListOf<WeatherModel>()

    val currentWeather:LiveData<List<WeatherModel>> = currentWeatherLiveData

//    init {
//        viewModelScope.launch {
//            val apiService = ApiInterface()
//            for (cityName in nameOfCities) {
//                val currentWeatherResponse = apiService.getCurrentWeather(cityName).await()
//                currentWeatherItems.add(currentWeatherResponse)
//            }
//
//            currentWeatherLiveData.value = currentWeatherItems
//        }
//    }
}