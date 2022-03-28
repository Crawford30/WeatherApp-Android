package com.example.weatherapp.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.models.weathermodel.WeatherModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentWeatherDao {
    @Query("SELECT * FROM current_weather")
    fun getAllCurrentWeather():Flow<List<WeatherModel>> //Flow will receive a list one after another

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeather:List<WeatherModel>)

    //Update the Cache
    @Query("DELETE  FROM current_weather")
    suspend fun deleteAllCurrentWeather()

}