package com.example.calculator

import com.example.calculator.model.CalculationHistory
import com.example.calculator.model.Operation
import com.example.calculator.model.CalculatorState
import org.junit.Assert.*
import org.junit.Test

/**
 * Unit тестове за моделите
 */
class ModelTest {

    // ========== Тестове за CalculationHistory ==========

    @Test
    fun `calculation history stores expression correctly`() {
        val history = CalculationHistory(
            expression = "5 + 3",
            result = "8"
        )
        assertEquals("5 + 3", history.expression)
        assertEquals("8", history.result)
    }

    @Test
    fun `calculation history formats time correctly`() {
        val history = CalculationHistory(
            expression = "10 - 5",
            result = "5"
        )
        // Проверяваме че времето не е празно
        assertTrue(history.getFormattedTime().isNotEmpty())
    }

    // ========== Тестове за Operation ==========

    @Test
    fun `operation enum has correct symbols`() {
        assertEquals("+", Operation.ADD.symbol)
        assertEquals("-", Operation.SUBTRACT.symbol)
        assertEquals("×", Operation.MULTIPLY.symbol)
        assertEquals("÷", Operation.DIVIDE.symbol)
        assertEquals("%", Operation.PERCENT.symbol)
        assertEquals("", Operation.NONE.symbol)
    }

    @Test
    fun `operation fromSymbol returns correct operation`() {
        assertEquals(Operation.ADD, Operation.fromSymbol("+"))
        assertEquals(Operation.SUBTRACT, Operation.fromSymbol("-"))
        assertEquals(Operation.MULTIPLY, Operation.fromSymbol("×"))
        assertEquals(Operation.DIVIDE, Operation.fromSymbol("÷"))
    }

    @Test
    fun `operation fromSymbol returns NONE for unknown symbol`() {
        assertEquals(Operation.NONE, Operation.fromSymbol("^"))
        assertEquals(Operation.NONE, Operation.fromSymbol("unknown"))
    }

    // ========== Тестове за CalculatorState ==========

    @Test
    fun `calculator state default values`() {
        val state = CalculatorState()
        assertEquals("0", state.currentNumber)
        assertEquals("", state.previousNumber)
        assertEquals(Operation.NONE, state.operation)
        assertEquals("0", state.displayText)
        assertTrue(state.isNewNumber)
        assertFalse(state.hasError)
    }

    @Test
    fun `calculator state hasOperation returns false for NONE`() {
        val state = CalculatorState(operation = Operation.NONE)
        assertFalse(state.hasOperation())
    }

    @Test
    fun `calculator state hasOperation returns true for ADD`() {
        val state = CalculatorState(operation = Operation.ADD)
        assertTrue(state.hasOperation())
    }

    @Test
    fun `calculator state getFullExpression without operation`() {
        val state = CalculatorState(
            currentNumber = "5",
            operation = Operation.NONE
        )
        assertEquals("5", state.getFullExpression())
    }

    @Test
    fun `calculator state getFullExpression with operation`() {
        val state = CalculatorState(
            currentNumber = "3",
            previousNumber = "5",
            operation = Operation.ADD
        )
        assertEquals("5 + 3", state.getFullExpression())
    }
}
