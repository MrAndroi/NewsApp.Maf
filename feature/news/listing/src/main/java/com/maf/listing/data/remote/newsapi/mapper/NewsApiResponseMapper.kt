package com.maf.listing.data.remote.newsapi.mapper

import com.maf.core.formatter.date.DateFormatter
import com.maf.core.mapper.Mapper
import com.maf.core.utils.Constants.DEFAULT_IMAGE
import com.maf.listing.data.models.NewsApiListingResponse
import com.maf.listing.domain.models.NewsListingModel
import javax.inject.Inject

class NewsApiResponseMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) : Mapper<NewsApiListingResponse, NewsListingModel>() {

    override fun map(source: NewsApiListingResponse): NewsListingModel {
        return NewsListingModel(
            title = source.title,
            author = source.author ?: "Unknown Author",
            link = source.link,
            image = source.image_url ?: DEFAULT_IMAGE,
            publishDate = dateFormatter.format(source.publish_date)
        )
    }

}