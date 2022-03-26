package com.example.weatherapp.database



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.utils.ListConverters
import com.example.weatherapp.utils.StringConverters


@Database(entities = [WeatherModel::class], version = 1, exportSchema = false)

@TypeConverters(*[(ListConverters::class), (StringConverters::class)])
abstract class CurrentWeatherDatabase : RoomDatabase() {

    //Create instance of interface
    abstract fun currentWeatherDao(): CurrentWeatherDao

    //Database needs to be a singleton
    companion object {
        @Volatile
        private var instance: CurrentWeatherDatabase? =
            null //@Volatile all threads will have immediate action on it

        private val LOCK = Any() //use to mean no threads is currently doing the same thing

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) { //  not null
            //check if instance is already initialized
            instance ?: buildDatabase(context).also {
                instance = it
            } //whatever is returned, we set the instance equal to it
        }

        //initialize db
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CurrentWeatherDatabase::class.java, "forecast.db"
        )
            .build()

    }
}