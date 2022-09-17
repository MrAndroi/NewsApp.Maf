package com.maf.core.formatter

interface Formatter<T> {

    fun format(value: T?): String
}