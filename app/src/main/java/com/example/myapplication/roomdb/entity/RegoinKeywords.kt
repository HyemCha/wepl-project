package com.example.myapplication.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "region_keywords",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Region::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("region_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class RegionKeywords(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "region_id") val retionId: String?,
    @ColumnInfo(name = "keywords") val keywords: String?
)
