package com.example.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.model.Operation

class CalculatorViewModel : ViewModel() {

    private val _displayText = MutableLiveData("0")
    val displayText: LiveData<String> = _displayText

    private val _expressionText = MutableLiveData("")
    val expressionText: LiveData<String> = _expressionText

    private var currentNumber = StringBuilder()
    private var previousNumber = 0.0
    private var lastResult = 0.0
    private var currentOperation: Operation? = null
    private var isNewNumber = true

    /** Цифри */
    fun onNumberClick(num: String) {
        if (isNewNumber) {
            currentNumber.clear()
            isNewNumber = false
        }
        currentNumber.append(num)
        _displayText.value = currentNumber.toString()
    }

    /** Десетична запетая */
    fun onDecimalClick() {
        if (!currentNumber.contains(".")) {
            if (isNewNumber) {
                currentNumber.clear()
                currentNumber.append("0.")
                isNewNumber = false
            } else {
                currentNumber.append(".")
            }
            _displayText.value = currentNumber.toString()
        }
    }

    /** Плюс/минус */
    fun onPlusMinusClick() {
        if (currentNumber.isNotEmpty()) {
            if (currentNumber.startsWith("-"))
                currentNumber.delete(0, 1)
            else
                currentNumber.insert(0, "-")
        }
        _displayText.value = currentNumber.toString()
    }

    /** Процент */
    fun onPercentClick() {
        val value = currentNumber.toString().toDoubleOrNull() ?: return
        val result = value / 100
        currentNumber = StringBuilder(result.toString())
        _displayText.value = currentNumber.toString()
    }

    /** Скоби – визуален ефект */
    fun onBracketsClick() {
        val expr = _expressionText.value ?: ""
        _expressionText.value = "$expr()"
    }

    /** Операции: + - × ÷ */
    fun onOperationClick(op: Operation) {
        val number = currentNumber.toString().toDoubleOrNull() ?: return

        if (currentOperation != null) {
            calculate()
        } else {
            lastResult = number
        }

        previousNumber = lastResult
        currentOperation = op
        isNewNumber = true

        _expressionText.value = "$previousNumber ${op.symbol}"
    }

    /** = */
    fun onEqualsClick() {
        calculate()
        currentOperation = null
        _expressionText.value = ""
    }

    private fun calculate() {
        val number = currentNumber.toString().toDoubleOrNull() ?: return

        lastResult = when (currentOperation) {
            Operation.ADD -> previousNumber + number
            Operation.SUBTRACT -> previousNumber - number
            Operation.MULTIPLY -> previousNumber * number
            Operation.DIVIDE ->
                if (number == 0.0) 0.0 else previousNumber / number
            else -> number
        }

        currentNumber = StringBuilder(lastResult.toString())
        _displayText.value = lastResult.toString()
        isNewNumber = true
    }

    /** C */
    fun clear() {
        currentNumber.clear()
        currentNumber.append("0")
        previousNumber = 0.0
        lastResult = 0.0
        currentOperation = null
        isNewNumber = true
        _displayText.value = "0"
        _expressionText.value = ""
    }
}
