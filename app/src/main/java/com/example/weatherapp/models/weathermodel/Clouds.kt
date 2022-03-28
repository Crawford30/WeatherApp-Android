package com.example.weatherapp.models.weathermodel


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Clouds(
    var all: Int
)

    : Parcelable{
    constructor(parcel: Parcel

    ) : this(
        parcel.readInt(),
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, p1: Int) {
        parcel?.writeInt(all)
    }

    companion object CREATOR : Parcelable.Creator<Clouds> {
        override fun createFromParcel(parcel: Parcel): Clouds {
            return Clouds(parcel)
        }

        override fun newArray(size: Int): Array<Clouds?> {
            return arrayOfNulls(size)
        }
    }
}