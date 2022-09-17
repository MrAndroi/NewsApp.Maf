package com.maf.core.formatter.di

import com.maf.core.formatter.date.DateFormatter
import com.maf.core.formatter.date.DateFormatterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FormatterModule {

    @Binds
    fun bindsDateFormatter(formatter: DateFormatterImpl): DateFormatter
}