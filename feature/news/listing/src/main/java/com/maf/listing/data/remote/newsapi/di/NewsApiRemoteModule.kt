package com.maf.listing.data.remote.newsapi.di

import com.maf.core.network.NetworkModule.NewsApi
import com.maf.listing.data.remote.newsapi.NewsApiListingRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create


@InstallIn(SingletonComponent::class)
@Module
object NewsApiRemoteModule {

    @Provides
    fun provideNewsApiListingRemoteDataSource(@NewsApi retrofit: Retrofit): NewsApiListingRemoteDataSource {
        return retrofit.create()
    }
}