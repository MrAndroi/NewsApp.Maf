package com.maf.listing.data.remote.datanews.di

import com.maf.core.network.NetworkModule.DataNews
import com.maf.listing.data.remote.datanews.NewsDataListingRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NewsDataRemoteModule {

    @Provides
    fun provideDataNewsListingRemoteDataSource(@DataNews retrofit: Retrofit): NewsDataListingRemoteDataSource {
        return retrofit.create()
    }

}