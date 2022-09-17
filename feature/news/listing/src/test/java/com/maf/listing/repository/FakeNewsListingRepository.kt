package com.maf.listing.repository

import com.maf.listing.domain.NewsListingRepository
import com.maf.listing.domain.models.NewsListingModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred

class FakeNewsListingRepository : NewsListingRepository {

    val listOne = listOf(
        NewsListingModel("TestApi1","Sami","12/09/2022","TestImage","TestLink")
    )

    val listTwo = listOf(
        NewsListingModel("TestApi2","Sami2","22/09/2022","TestImage2","TestLink2")
    )

    override suspend fun getNewsFromDataNewsAsync(
        country: String,
        category: String
    ): Deferred<List<NewsListingModel>> {
        return CompletableDeferred(listOne)
    }

    override suspend fun getNewsFromNewsApiAsync(
        country: String,
        category: String
    ): Deferred<List<NewsListingModel>> {
        return CompletableDeferred(listTwo)
    }

}