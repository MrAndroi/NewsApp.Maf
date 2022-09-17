package com.maf.core.formatter.date

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DateFormatterTest {

    private lateinit var dateFormatter: DateFormatter

    @Before
    fun setUp() {
        dateFormatter = DateFormatterImpl()
    }

    @Test
    fun `Format date from yyyyMMdd hhmmss to ddMMyyyy`() {
        val unFormattedDate = "2000-09-15 07:07:0"
        val formattedDate = "15/09/2000"
        assertThat(formattedDate).isEqualTo(dateFormatter.format(unFormattedDate))
    }

    @Test
    fun `Format date from yyyy-MM-dd'T'HHmmss to ddMMyyyy`() {
        val unFormattedDate = "2022-09-16T13:08:00Z"
        val formattedDate = "16/09/2022"
        assertThat(formattedDate).isEqualTo(dateFormatter.format(unFormattedDate))
    }

}