package com.example.weatherapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherModel
import kotlinx.android.synthetic.main.layout_weather_row.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.R.string


class WeatherAdapter(var currentWeatherList: ArrayList<WeatherModel>) :
    RecyclerView.Adapter<CurrentWeatherVH>(), Filterable {

    var currentWeatherFilteredList = ArrayList<WeatherModel>()


    init {
        currentWeatherFilteredList = currentWeatherList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherVH {
        val layoutInflater = LayoutInflater.from(parent?.context)

        val cellForRow = layoutInflater.inflate(R.layout.layout_weather_row, parent, false)

        return CurrentWeatherVH(cellForRow)
    }

    override fun onBindViewHolder(holder: CurrentWeatherVH, position: Int) {

        var currentWeatherData = currentWeatherFilteredList[position]

        if (currentWeatherData != null) {

            holder?.view?.city_name.text = currentWeatherData.name
            val longDate = epochToIso8601(currentWeatherData.dt.toLong())

            var stringDate: String = longDate.toString()



            var date = stringDate.substring(0, 11)

            var time = stringDate.substring(12, 20)

            holder?.view.date_tv.text = date
            holder?.view.time_tv.text = time
            holder?.view.temperature_tv.text = currentWeatherData.main.temp.toString() + "°C"

            Glide.with(holder?.view.context)
                .load("https://openweathermap.org/img/wn/" + currentWeatherData.weather[0].icon + "@2x.png")
                .into(holder?.view.temp_icon)

        }


    }

    override fun getItemCount(): Int {
        return currentWeatherFilteredList.size
    }


    private fun epochToIso8601(time: Long): String {
        val format = "dd MMM yyyy HH:mm aa" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charSearch = constraint.toString()

                if (charSearch.isEmpty()) {

                    currentWeatherFilteredList = currentWeatherList

                } else {


                    var resultList = ArrayList<WeatherModel>()
                    for (row in currentWeatherList) {
                        if (row.name.toLowerCase()
                                .contains(charSearch.toLowerCase())
                        ) {

                            resultList.add(row)
                        }

                    }

                    currentWeatherFilteredList = resultList

                }

                val filterResults = FilterResults()
                filterResults.values = currentWeatherFilteredList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                currentWeatherFilteredList = results?.values as ArrayList<WeatherModel>

                notifyDataSetChanged()
            }
        }

    }

}

class CurrentWeatherVH(val view: View, var currentWeather: WeatherModel? = null) :
    RecyclerView.ViewHolder(view) {

    init {

        view.setOnClickListener {
            val context = view.context

//            val intent = Intent(context, PraisesAndWorshipDetails::class.java).apply{
//
//
//                Toast.makeText(context,
//                    praisesAndWorshipSongs?.praisesTitle,
//                    Toast.LENGTH_LONG).show()
//
//
//                //send data using intent
//                putExtra("PRAISESNUMBER", praisesAndWorshipSongs?.praisesNumber)
//                putExtra("PRAISESTITLE", praisesAndWorshipSongs?.praisesTitle)
//                putExtra("PRAISESLYRIC", praisesAndWorshipSongs?.praisesLyric)
//
//
//
//            }

            // context.startActivity(intent)


        }
    }

}




