package com.example.myapplication.youtubeapi

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Root

// 마들 클라쓰

data class YouTubeResponse(
    @SerializedName("kind"     ) var kind     : String?          = null,
    @SerializedName("etag"     ) var etag     : String?          = null,
    @SerializedName("items"    ) var items    : ArrayList<Items> = arrayListOf(),
    @SerializedName("pageInfo" ) var pageInfo : PageInfo?        = PageInfo()
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

@Root(name = "maxres", strict = false)
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

data class ResourceId (
    @SerializedName("kind"    ) var kind    : String? = null,
    @SerializedName("videoId" ) var videoId : String? = null
)

@Root(name = "snippet", strict = false)
data class Snippet(
    @SerializedName("publishedAt"            ) var publishedAt            : String?     = null,
    @SerializedName("channelId"              ) var channelId              : String?     = null,
    @SerializedName("title"                  ) var title                  : String?     = null,
    @SerializedName("description"            ) var description            : String?     = null,
    @SerializedName("thumbnails"             ) var thumbnails             : Thumbnails? = Thumbnails(),
    @SerializedName("channelTitle"           ) var channelTitle           : String?     = null,
    @SerializedName("playlistId"             ) var playlistId             : String?     = null,
    @SerializedName("position"               ) var position               : Int?        = null,
    @SerializedName("resourceId"             ) var resourceId             : ResourceId? = ResourceId(),
    @SerializedName("videoOwnerChannelTitle" ) var videoOwnerChannelTitle : String?     = null,
    @SerializedName("videoOwnerChannelId"    ) var videoOwnerChannelId    : String?     = null
)

data class Items(
    @SerializedName("kind"    ) var kind    : String?  = null,
    @SerializedName("etag"    ) var etag    : String?  = null,
    @SerializedName("id"      ) var id      : String?  = null,
    @SerializedName("snippet" ) var snippet : Snippet? = Snippet()
)
data class PageInfo (
    @SerializedName("totalResults"   ) var totalResults   : Int? = null,
    @SerializedName("resultsPerPage" ) var resultsPerPage : Int? = null
)