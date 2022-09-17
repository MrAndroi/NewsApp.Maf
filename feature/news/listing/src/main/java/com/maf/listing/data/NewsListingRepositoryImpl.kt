package com.maf.listing.data

import com.maf.listing.data.remote.datanews.NewsDataListingRemoteDataSource
import com.maf.listing.data.remote.datanews.mapper.DataNewsResponseMapper
import com.maf.listing.data.remote.newsapi.NewsApiListingRemoteDataSource
import com.maf.listing.data.remote.newsapi.mapper.NewsApiResponseMapper
import com.maf.listing.domain.NewsListingRepository
import com.maf.listing.domain.models.NewsListingModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsListingRepositoryImpl @Inject constructor(
    private val newsDataListingRemoteDataSource: NewsDataListingRemoteDataSource,
    private val newsApiListingRemoteDataSource: NewsApiListingRemoteDataSource,
    private val dataNewsResponseMapper: DataNewsResponseMapper,
    private val newsApiResponseMapper: NewsApiResponseMapper
) : NewsListingRepository {

    override suspend fun getNewsFromDataNewsAsync(
        country: String,
        category: String
    ): Deferred<List<NewsListingModel>> {
        return withContext(Dispatchers.IO) {
            async {
                runCatching {
                    newsDataListingRemoteDataSource.getLatestNewsFromDataNews(
                        country,
                        category
                    )
                }
                    .map { dataNewsResponseMapper.mapList(it) }
                    .getOrDefault(emptyList())
            }
        }
    }

    override suspend fun getNewsFromNewsApiAsync(
        country: String,
        category: String
    ): Deferred<List<NewsListingModel>> {
        return withContext(Dispatchers.IO) {
            async {
                runCatching {
                    newsApiListingRemoteDataSource.getLatestNewsFromNewsApi(
                        country,
                        category
                    )
                }
                    .map { newsApiResponseMapper.mapList(it) }
                    .getOrDefault(emptyList())
            }
        }
    }
}