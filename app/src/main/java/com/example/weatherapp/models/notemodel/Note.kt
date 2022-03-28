package com.example.weatherapp.models.notemodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*



@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteBody: String
) : Parcelable


//@Entity(tableName = "notes")
//data class Note(
//    @PrimaryKey
//    @ColumnInfo(name = "dateAdded")
//    val dateAdded: Date,
//    @ColumnInfo(name = "noteText")
//    val noteText: String,
////    @ColumnInfo(name = "lastUpdate")
////    val lastUpdate: Int
//)