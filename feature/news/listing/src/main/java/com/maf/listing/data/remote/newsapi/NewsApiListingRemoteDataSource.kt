package com.maf.listing.data.remote.newsapi

import com.maf.listing.BuildConfig
import com.maf.listing.data.models.NewsApiListingResponse
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiListingRemoteDataSource {

    @GET("v2/top-headlines")
    @Wrapped(path = ["articles"])
    suspend fun getLatestNewsFromNewsApi(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
    ) : List<NewsApiListingResponse>

}