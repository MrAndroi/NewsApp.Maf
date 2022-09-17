package com.maf.listing.data.di

import com.maf.listing.data.NewsListingRepositoryImpl
import com.maf.listing.domain.NewsListingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NewsListingModule {

    @Binds
    fun bindNewsListingRepository(newsListingRepositoryImpl: NewsListingRepositoryImpl): NewsListingRepository

}