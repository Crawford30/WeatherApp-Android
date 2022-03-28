package com.example.weatherapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.example.weatherapp.databinding.ActivityDetailBinding
import com.example.weatherapp.ui.MainActivity
import com.example.weatherapp.ui.weather.CurrentWeatherFragment
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    lateinit var toolBar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)




        city_name_detail.text = intent.getStringExtra("CITYNAME")
        status_tv.text = intent.getStringExtra("STATUS")


        val tempMin = intent.getStringExtra("MINTEMP")
        val tempMax = intent.getStringExtra("MAXTEMP")

        temp_min.text = "Min Temp: ${tempMin.toString()} " + "°C"
        temp_max.text = "Max Temp:  ${tempMax.toString()} " + "°C"
        val tempText = intent.getStringExtra("TEMP")

        temp.text = "$tempText" + "°C"


        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}