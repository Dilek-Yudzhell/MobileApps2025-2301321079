package com.example.calculator.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CalculationHistory(
    val id: Long = System.currentTimeMillis(),
    val expression: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
) {
    fun getFormattedTime(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
