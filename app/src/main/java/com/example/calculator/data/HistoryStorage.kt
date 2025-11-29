package com.example.calculator.data

import com.example.calculator.model.CalculationHistory

object HistoryStorage {
    private val historyList = mutableListOf<CalculationHistory>()
    private const val MAX_HISTORY_SIZE = 100

    fun addHistory(history: CalculationHistory) {
        historyList.add(0, history)
        if (historyList.size > MAX_HISTORY_SIZE) {
            historyList.removeAt(historyList.lastIndex)
        }
    }

    fun getAllHistory(): List<CalculationHistory> {
        return historyList.toList()
    }

    fun clearHistory() {
        historyList.clear()
    }

    fun getHistoryCount(): Int = historyList.size
}
