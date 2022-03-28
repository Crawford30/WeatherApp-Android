package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.models.weathermodel.WeatherModel
import kotlinx.android.synthetic.main.layout_weather_row.view.*
import kotlin.collections.ArrayList
import com.example.weatherapp.utils.epochToIso8601
import java.util.*


class WeatherAdapter(
    var currentWeatherList: ArrayList<WeatherModel>
) :
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

        var currentWeatherData = currentWeatherFilteredList.get(position)

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

        holder?.currentWeatherItem = currentWeatherData


    }

    override fun getItemCount(): Int {
        return currentWeatherFilteredList.size
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
                        if (row.name.lowercase(Locale.getDefault())
                                .contains(charSearch.lowercase(Locale.getDefault()))
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

class CurrentWeatherVH(val view: View, var currentWeatherItem: WeatherModel? = null) :
    RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {

            val context = view.context


        }

        view.is_favourite.setOnClickListener {
            view.is_favourite.setBackgroundResource(R.drawable.ic_baseline_favorite)

        }
    }


}







