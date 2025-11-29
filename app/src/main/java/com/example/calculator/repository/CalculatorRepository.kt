package com.example.calculator.repository

import com.example.calculator.data.HistoryStorage
import com.example.calculator.model.CalculationHistory

class CalculatorRepository {

    fun saveCalculation(expression: String, result: String) {
        val history = CalculationHistory(
            expression = expression,
            result = result
        )
        HistoryStorage.addHistory(history)
    }

    fun getHistory(): List<CalculationHistory> {
        return HistoryStorage.getAllHistory()
    }

    fun clearHistory() {
        HistoryStorage.clearHistory()
    }

    fun calculate(firstNumber: Double, secondNumber: Double, operator: String): String {
        return try {
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "×" -> firstNumber * secondNumber
                "÷" -> {
                    if (secondNumber == 0.0) {
                        return "Грешка"
                    }
                    firstNumber / secondNumber
                }
                "%" -> firstNumber * (secondNumber / 100)
                else -> return "Грешка"
            }
            formatResult(result)
        } catch (e: Exception) {
            "Грешка"
        }
    }

    private fun formatResult(result: Double): String {
        return if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            String.format("%.8f", result).trimEnd('0').trimEnd('.')
        }
    }
}
