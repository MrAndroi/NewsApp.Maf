package com.maf.core.formatter.date

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import javax.inject.Inject

class DateFormatterImpl @Inject constructor() : DateFormatter {

    @SuppressLint("SimpleDateFormat")
    override fun format(value: String?): String {
        return value?.let {
            val pattern1 = "yyyy-MM-dd hh:mm:ss"
            val pattern2 = "yyyy-MM-dd'T'HH:mm:ss"
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            lateinit var parser:SimpleDateFormat
            return if(value.contains('T')) {
                parser = SimpleDateFormat(pattern2)
                formatter.format(parser.parse(it)!!)
            } else {
                parser = SimpleDateFormat(pattern1)
                formatter.format(parser.parse(it)!!)
            }

        } ?: ""
    }
}