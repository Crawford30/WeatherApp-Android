package com.example.weatherapp.ui.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.ApiInterface
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.utils.nameOfCities
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrentWeatherFragment : Fragment() {

    //private var currentWeatherItems = MutableList


    ///private var currentWeatherItems: MutableList<WeatherModel> = ArrayList()

    private var currentWeatherItems = arrayListOf<WeatherModel>()

    lateinit var currentWeatherAdapter: WeatherAdapter

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel



        if(activity != null && isAdded) {

            setData()

        }



        swipe_refresh_layout.setOnRefreshListener {

            if(activity != null && isAdded) {
            setData()
            swipe_refresh_layout.isRefreshing = false
            }
        }


    }


    private fun setData() {
        val apiService = ApiInterface()

        GlobalScope.launch(Dispatchers.Main) {


            for (cityName in nameOfCities) {
                val currentWeatherResponse = apiService.getCurrentWeather(cityName).await()

                currentWeatherItems.add(currentWeatherResponse)
            }
            //val currentWeatherResponse = apiService.getCurrentWeather("London").await()


            current_weather_recycler.apply {

                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)

//                val topSPacingDecoration = TopSpacingItemDecoration(20)
//                addItemDecoration(topSPacingDecoration)

                currentWeatherAdapter = WeatherAdapter(currentWeatherItems)


                adapter = currentWeatherAdapter


//                ten_major_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        return false
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        tenMajorAdapter.filter.filter(newText)
//                        return false
//                    }
//
//                })


            }


        }

    }

}