package com.example.weatherapp

import android.util.Log
import com.example.weatherapp.models.weathermodel.WeatherModel
import com.example.weatherapp.network.ConnectivityInterceptor
import com.example.weatherapp.utils.API_KEY
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.openweathermap.org/data/2.5/weather?q=London&APPID=04a42b96398abc8e4183798ed22f9485

interface ApiInterface {

    @GET("weather")
    fun getCurrentWeather(
        @Query(value = "q") location: String
    ): Deferred<WeatherModel>



    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor):ApiInterface{
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("APPID", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                println("URL: ${request}")
                Log.d("REQUEST", "REQUEST: ${request}")
                return@Interceptor chain.proceed(request) //proceed with the request we created

            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            //Api response thru retrofit
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

        }
    }
}