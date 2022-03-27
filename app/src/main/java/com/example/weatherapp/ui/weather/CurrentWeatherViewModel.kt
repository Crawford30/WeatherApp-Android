package com.example.weatherapp.ui.weather

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp.ApiInterface
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.repository.CurrentWeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrentWeatherViewModel constructor(private val repository: CurrentWeatherRepository) : ViewModel() {


    val movieList = MutableLiveData<List<WeatherModel>>()
    val errorMessage = MutableLiveData<String>()



//    private val repository:CurrentWeatherRepository
//    private var readAll:<List<WeatherModel>>


//   private val currentWeatherLiveData = MutableLiveData<List<WeatherModel>>()
//
//    private var currentWeatherItems = arrayListOf<WeatherModel>()
//
//    val currentWeather:LiveData<List<WeatherModel>> = currentWeatherLiveData

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