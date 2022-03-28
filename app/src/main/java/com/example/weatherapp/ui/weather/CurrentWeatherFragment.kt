package com.example.weatherapp.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.weatherapp.ApiInterface

import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.database.CurrentWeatherDatabase
import com.example.weatherapp.models.weathermodel.WeatherModel
import com.example.weatherapp.network.ConnectivityInterceptorImpl
import com.example.weatherapp.utils.NoConnectivityException
import com.example.weatherapp.utils.nameOfCities
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.R


class CurrentWeatherFragment : Fragment() {


    private var currentWeatherItems = arrayListOf<WeatherModel>()

    lateinit var currentWeatherAdapter: WeatherAdapter

    companion object {

        var database: CurrentWeatherDatabase? = null
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null && isAdded) {

            setData()

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        database = Room.databaseBuilder(
            requireActivity().applicationContext, CurrentWeatherDatabase::class.java,
            "forecast.db"
        ).build()

        val weatherDao = database!!.currentWeatherDao()


        val allWeather: Flow<List<WeatherModel>> = weatherDao.getAllCurrentWeather()

        Log.d("ALL", "${allWeather}")




        swipe_refresh_layout.setOnRefreshListener {

            if (activity != null && isAdded) {
                setData()
                swipe_refresh_layout.isRefreshing = false
            }
        }


    }


    @SuppressLint("UseRequireInsteadOfGet")
    private fun setData() {

        val apiService = ApiInterface(ConnectivityInterceptorImpl(this.context!!))

        GlobalScope.launch(Dispatchers.Main) {

            try {

                for (cityName in nameOfCities) {
                    val currentWeatherResponse = apiService.getCurrentWeather(cityName).await()

                    currentWeatherItems.add(currentWeatherResponse)
                }

            } catch (e: NoConnectivityException) {
                Log.e("Connectivity", "No Internet Connection", e)
            }


            val recyclerView: RecyclerView =
                view!!.findViewById(R.id.current_weather_recycler)
            var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
            recyclerView.layoutManager = layoutManager

            val search = view!!.findViewById<SearchView>(R.id.weather_search)


            // define an adapter
            currentWeatherAdapter = WeatherAdapter(currentWeatherItems)
            recyclerView.adapter = currentWeatherAdapter

            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    currentWeatherAdapter.filter.filter(newText)
                    return false
                }

            })

        }

    }


}