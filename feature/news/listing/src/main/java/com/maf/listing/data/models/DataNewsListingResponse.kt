package com.maf.listing.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataNewsListingResponse(
    @Json(name = "creator")
    val authors: List<String>?,
    @Json(name = "image_url")
    val image_url: String?,
    @Json(name = "link")
    val link: String,
    @Json(name = "pubDate")
    val publish_date: String,
    @Json(name = "title")
    val title: String
)