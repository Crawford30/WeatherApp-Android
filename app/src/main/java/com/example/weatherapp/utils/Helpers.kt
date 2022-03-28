package com.example.weatherapp.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

 fun epochToIso8601(time: Long): String {
    val format = "dd MMM yyyy HH:mm a" // you can add the format you need
    val sdf = SimpleDateFormat(format, Locale.getDefault()) // default local
    sdf.timeZone = TimeZone.getDefault() // set anytime zone you need
    return sdf.format(Date(time * 1000))
}


fun Context.toast(message: String){
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
}