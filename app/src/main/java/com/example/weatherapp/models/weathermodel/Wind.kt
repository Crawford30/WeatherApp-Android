package com.example.weatherapp.models.weathermodel


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Wind(
    val deg: Double,
    val speed: Double
)

    : Parcelable{
    constructor(parcel: Parcel):this(
        parcel.readDouble(),
        parcel.readDouble(),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(deg)
        parcel.writeDouble(speed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wind> {
        override fun createFromParcel(parcel: Parcel): Wind {
            return Wind(parcel)
        }

        override fun newArray(size: Int): Array<Wind?> {
            return arrayOfNulls(size)
        }
    }
}