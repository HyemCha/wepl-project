package com.example.myapplication.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "song_keywords",
    foreignKeys = [ForeignKey(
        entity = Song::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("song_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SongKeywords(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "song_id") val songId: Int?,
    @ColumnInfo(name = "keywords") val keywords: String?
)
