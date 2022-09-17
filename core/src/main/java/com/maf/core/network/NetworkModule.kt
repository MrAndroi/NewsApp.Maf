package com.maf.core.network

import com.maf.core.BuildConfig
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @NewsApi
    fun provideRetrofitForNewsApi(factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_API_BASE_URL)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @DataNews
    fun provideRetrofitForDataNews(factory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_DATA_BASE_URL)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Wrapped.ADAPTER_FACTORY)
            .build()
    }

    @Qualifier
    @Retention
    annotation class NewsApi

    @Qualifier
    @Retention
    annotation class DataNews
}