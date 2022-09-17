package com.maf.listing.domain

import com.maf.listing.domain.models.NewsListingModel
import kotlinx.coroutines.Deferred

interface NewsListingRepository  {
    suspend fun getNewsFromDataNewsAsync(country: String, category: String): Deferred<List<NewsListingModel>>
    suspend fun getNewsFromNewsApiAsync(country: String, category: String): Deferred<List<NewsListingModel>>
}