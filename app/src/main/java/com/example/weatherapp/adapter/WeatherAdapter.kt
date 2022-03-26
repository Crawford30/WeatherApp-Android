package com.example.weatherapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class WeatherAdapter(private val currentWeatherList: ArrayList<WeatherModel>):RecyclerView.Adapter<CurrentWeatherVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentWeatherVH {
        val layoutInflater = LayoutInflater.from(parent?.context)

        val cellForRow = layoutInflater.inflate(R.layout.layout_weather_row, parent, false)

        return  CurrentWeatherVH(cellForRow)
    }

    override fun onBindViewHolder(holder: CurrentWeatherVH, position: Int) {

        var currentWeatherData = currentWeatherList[position]

        if(currentWeatherData != null){



            holder?.view?.city_name.text = currentWeatherData.name

            holder?.view.date_tv.text = epochToIso8601(currentWeatherData.dt.toLong())

            holder?.view.temperature_tv.text = currentWeatherData.main.temp.toString() + "°C"

            Glide.with(holder?.view.context)
                .load("https://openweathermap.org/img/wn/" + currentWeatherData.weather[0].icon + "@2x.png")
                .into( holder?.view.temp_icon)

        }


    }

    override fun getItemCount(): Int {
        return currentWeatherList.size
    }


    private fun epochToIso8601(time: Long): String {
        val format = "dd MMM yyyy HH:mm:ss" // you can add the format you need
        val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
        sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
        return sdf.format(Date(time * 1000))
    }

}




class CurrentWeatherVH(val view: View, var currentWeather: WeatherModel? = null):RecyclerView.ViewHolder(view) {

    companion object {
        val TEN_MAJOR_NUMBER = "TEN_MAJOR_NUMBER"
        val TEN_MAJOR_TITLE = "TEN_MAJOR_TITLE"
        val TEN_MAJOR_LYRIC = "TEN_MAJOR_LYRIC"

    }

    init {
        view.setOnClickListener {

//            val context = view.context
//
//            val intent = Intent(context, GodTenMajorDetails::class.java).apply {
//
//                Toast.makeText(context,
//                    tenMajorSongs?.tenMajorTitle,
//                    Toast.LENGTH_LONG).show()
//
//
//                putExtra("TENMAJORNUMBER", tenMajorSongs?.tenMajorNumber)
//                putExtra("TENMAJORTITLE", tenMajorSongs?.tenMajorTitle)
//                putExtra("TENMAJORLYRIC", tenMajorSongs?.tenMajorLyric)


//              putExtra(TEN_MAJOR_NUMBER, tenMajorSongs?.tenMajorNumber)
//              putExtra(TEN_MAJOR_TITLE, tenMajorSongs?.tenMajorTitle)
//              putExtra(TEN_MAJOR_LYRIC, tenMajorSongs?.tenMajorNumber)

            }

//            context.startActivity(intent)


        }

    class WeatherComparator: DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel) =
            oldItem == newItem

    }


}
