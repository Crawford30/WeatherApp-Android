package com.example.weatherapp.models.weathermodel

import android.os.Parcel
import android.os.Parcelable


data class Weather(
    val description: String?,
    val icon: String?,
    val main: String?
)

    : Parcelable{
    constructor(
        parcel: Parcel
    ):this(

        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(icon)
        parcel.writeString(main)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }
}