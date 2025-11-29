package com.example.calculator.model

data class CalculatorState(
    val currentNumber: String = "0",
    val previousNumber: String? = null,
    val operation: Operation? = null,
    val isNewNumber: Boolean = true
)
