package com.maf.listing.data.remote.datanews

import com.maf.listing.BuildConfig
import com.maf.listing.data.models.DataNewsListingResponse
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataListingRemoteDataSource {

    @GET("api/1/news")
    @Wrapped(path = ["results"])
    suspend fun getLatestNewsFromDataNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_DATA_KEY,
    ) : List<DataNewsListingResponse>
}