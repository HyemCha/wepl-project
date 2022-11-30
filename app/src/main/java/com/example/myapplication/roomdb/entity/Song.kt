package com.example.myapplication.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "song")
data class Song(
    @ColumnInfo(name = "region_id") val regionId: String?,
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "youtube_id") val youtubeId: String,
    @ColumnInfo(name = "keywords") val keywords: String?
)
