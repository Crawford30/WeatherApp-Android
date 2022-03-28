package com.example.weatherapp.models.weathermodel


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class WeatherModel(
    val base: String,

    @Embedded
    val clouds: Clouds,
    val cod: Double,

    @Embedded
    val coord: Coord,
    val dt: Int,

    @PrimaryKey
    val id: Int,
    @Embedded
    val main: Main,
    val name: String?,
    @Embedded
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    @Embedded
    val wind: Wind,

)



   // : Parcelable {
//    constructor(parcel: Parcel) : this(
//         parcel.readString(),
//
//        parcel.readParcelable<Clouds>(Clouds::class.java.classLoader),
//
//        parcel.readDouble(),
//
//        parcel.readParcelable<Coord>(Coord::class.java.classLoader),
//
//        parcel.readInt(),
//
//        parcel.readInt(),
//
//        parcel.readParcelable<Main>(Main::class.java.classLoader),
//
//        parcel.readString(),
//
//        parcel.readParcelable<Sys>(Sys::class.java.classLoader),
//
//        parcel.readInt(),
//
//        parcel.readInt(),
//        parcel.readParcelable<Wind>(Wind::class.java.classLoader),
//
//        parcel.readParcelable<Weather>(Weather::class.java.classLoader),
//
//
//        )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(base)
//        parcel.writeParcelable(clouds, flags)
//        parcel.writeDouble(cod)
//        parcel.writeParcelable(coord, flags)
//        parcel.writeInt(dt)
//        parcel.writeInt(id)
//        parcel.writeParcelable(main, flags)
//        parcel.writeString(name)
//        parcel.writeParcelable(sys, flags)
//        parcel.writeInt(timezone)
//        parcel.writeInt(visibility)
//        parcel.writeParcelable(weather, flags)
//        parcel.writeParcelable(wind, flags)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<WeatherModel> {
//        override fun createFromParcel(parcel: Parcel): WeatherModel {
//            return WeatherModel(parcel)
//        }
//
//        override fun newArray(size: Int): Array<WeatherModel?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
//
//
