package com.withpet.withpet_android.others

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDateTimeString(time: Date): String {
        val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd a HH:mm", Locale.ROOT)
        return dateTimeFormat.format(time)
    }

    fun timeToString(year: Int, month: Int, day: Int, hour: Int, minute: Int): String {
        val date = "${intToString(year)}-${intToString(month + 1)}-${intToString(day)}"
        val h = if (hour < 12) "AM ${intToString(hour)}" else "PM ${intToString(hour - 12)}"
        val m = intToString(minute)
        return "$date ${h}:${m}"
    }

    private fun intToString(num: Int) = if (num < 10) "0$num" else "$num"
}