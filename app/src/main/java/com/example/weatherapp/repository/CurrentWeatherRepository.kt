package com.example.weatherapp.repository

import com.example.weatherapp.database.Dao.CurrentWeatherDao
import com.example.weatherapp.models.weathermodel.WeatherModel
import kotlinx.coroutines.flow.Flow


class CurrentWeatherRepository(private val currentWeatherDao: CurrentWeatherDao) {

    suspend fun insertCurrentWeather(weatherViewModel: List<WeatherModel>) =
        currentWeatherDao.insertCurrentWeather(weatherViewModel)

    fun getAllCurrentWeather(): Flow<List<WeatherModel>> = currentWeatherDao.getAllCurrentWeather()
}


//class MainRepository constructor(private val retrofitService: RetrofitService) {
//    fun getAllMovies() = retrofitService.getAllMovies()
//}