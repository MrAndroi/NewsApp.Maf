package com.maf.listing.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.maf.listing.repository.FakeNewsListingRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNewsUseCaseTest {

    private lateinit var getNewsUseCase: GetNewsUseCase
    private lateinit var fakeNewsListingRepository: FakeNewsListingRepository

    @Before
    fun setUp() {
        fakeNewsListingRepository = FakeNewsListingRepository()
        getNewsUseCase = GetNewsUseCase(fakeNewsListingRepository)
    }

    @Test
    fun `Merge Two lists together`(): Unit = runBlocking {
        val mergedNews = getNewsUseCase("", "")

        assertThat(mergedNews).containsAnyIn(
            fakeNewsListingRepository.listOne
        )
        assertThat(mergedNews).containsAnyIn(
            fakeNewsListingRepository.listTwo
        )
    }


}