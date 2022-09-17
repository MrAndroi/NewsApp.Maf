package com.maf.listing.domain.usecase

import com.maf.listing.domain.NewsListingRepository
import com.maf.listing.domain.models.NewsListingModel
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsListingRepository: NewsListingRepository
) {

    suspend operator fun invoke(country: String, category: String): List<NewsListingModel> {
        val newsDataResponse =
            newsListingRepository.getNewsFromDataNewsAsync(country, category).await()
        val newsApiResponse =
            newsListingRepository.getNewsFromNewsApiAsync(country, category).await()

        return newsDataResponse.union(newsApiResponse).toList()
    }

}