package com.maf.listing.data.remote.datanews.mapper

import com.maf.core.formatter.date.DateFormatter
import com.maf.core.mapper.Mapper
import com.maf.core.utils.Constants.DEFAULT_IMAGE
import com.maf.listing.data.models.DataNewsListingResponse
import com.maf.listing.domain.models.NewsListingModel
import javax.inject.Inject

class DataNewsResponseMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) : Mapper<DataNewsListingResponse, NewsListingModel>() {

    override fun map(source: DataNewsListingResponse): NewsListingModel {
        return NewsListingModel(
            title = source.title,
            author = getAuthorName(source.authors),
            link = source.link,
            image = source.image_url?.trim() ?: DEFAULT_IMAGE,
            publishDate = dateFormatter.format(source.publish_date)
        )
    }

    private fun getAuthorName(authors: List<String>?): String {
       return authors?.joinToString(", ") ?: "Unknown Author"
    }

}