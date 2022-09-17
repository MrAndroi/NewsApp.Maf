package com.maf.listing.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsApiListingResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "publishedAt")
    val publish_date: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val link: String,
    @Json(name = "urlToImage")
    val image_url: String?
)