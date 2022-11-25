package com.example.myapplication.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "region")
data class Region (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "keywords") val keywords: String?
)