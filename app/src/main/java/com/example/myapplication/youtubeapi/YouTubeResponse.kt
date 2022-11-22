package com.example.myapplication.youtubeapi

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Root

// 마들 클라쓰

data class YouTubeResponse(
    @SerializedName("items" ) var items : ArrayList<Items> = arrayListOf()
)

@Root(name = "default", strict = false)
data class Default(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

@Root(name = "medium", strict = false)
data class Medium(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

@Root(name = "high", strict = false)
data class High(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

@Root(name = "standard", strict = false)
data class Standard(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

@Root(name = "maxred", strict = false)
data class Maxres(
    @SerializedName("url") var url: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("height") var height: Int? = null
)

@Root(name = "thumbnails", strict = false)
data class Thumbnails(
    @SerializedName("default") var default: Default? = Default(),
    @SerializedName("medium") var medium: Medium? = Medium(),
    @SerializedName("high") var high: High? = High(),
    @SerializedName("standard") var standard: Standard? = Standard(),
    @SerializedName("maxres") var maxres: Maxres? = Maxres()
)

@Root(name = "snippet", strict = false)
data class Snippet(
    @SerializedName("title"        ) var title        : String?     = null,
    @SerializedName("thumbnails"   ) var thumbnails   : Thumbnails? = Thumbnails(),
    @SerializedName("channelTitle" ) var channelTitle : String?     = null
)

@Root(name = "items", strict = false)
data class Items(
    @SerializedName("id") var id: String? = null,
    @SerializedName("snippet") var snippet: Snippet? = Snippet()
)