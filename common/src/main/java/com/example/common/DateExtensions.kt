package com.example.common

import java.lang.Exception
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val TARGET_FORMAT = "d MMMM yyyy -  h:mm a"
const val SERVER_TIME_ZONE = "UTC"

private val format = NumberFormat.getInstance(Locale.ENGLISH)

fun String.formatWithDeviceTimeZone(): String {
    return try {
        val originalFormat = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone(SERVER_TIME_ZONE)
        }
        val targetFormat = SimpleDateFormat(TARGET_FORMAT, Locale.getDefault()).apply {
            numberFormat = format
        }
        val createdDate = originalFormat.parse(this)?.let {
            targetFormat.format(it)
        } ?: this
        createdDate
    } catch (e: Exception) {
        return ""
    }
}