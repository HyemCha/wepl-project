package com.example.myapplication.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "region_keywords",
    foreignKeys = [ForeignKey(
        entity = Region::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("region_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class RegionKeywords(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "region_id") val regionId: Int?,
    @ColumnInfo(name = "keywords") val keywords: String?
)
