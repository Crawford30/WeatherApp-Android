package com.example.weatherapp.models.weathermodel


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Double,
    val pressure: Double,
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)
    : Parcelable{
    constructor(parcel: Parcel): this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),


    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeDouble(feelsLike)
        parcel?.writeDouble(humidity)
        parcel?.writeDouble(pressure)
        parcel?.writeDouble(temp)
        parcel?.writeDouble(tempMax)
        parcel?.writeDouble(tempMin)

    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }
}