package com.example.calculator

import com.example.calculator.repository.CalculatorRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit тестове за CalculatorRepository
 * Покриват основните математически операции
 */
class CalculatorRepositoryTest {

    private lateinit var repository: CalculatorRepository

    @Before
    fun setup() {
        repository = CalculatorRepository()
    }

    // ========== Тестове за събиране ==========

    @Test
    fun `addition of two positive numbers`() {
        val result = repository.calculate(5.0, 3.0, "+")
        assertEquals("8", result)
    }

    @Test
    fun `addition with zero`() {
        val result = repository.calculate(5.0, 0.0, "+")
        assertEquals("5", result)
    }

    @Test
    fun `addition of negative numbers`() {
        val result = repository.calculate(-5.0, -3.0, "+")
        assertEquals("-8", result)
    }

    @Test
    fun `addition with decimal numbers`() {
        val result = repository.calculate(2.5, 3.5, "+")
        assertEquals("6", result)
    }

    // ========== Тестове за изваждане ==========

    @Test
    fun `subtraction of two positive numbers`() {
        val result = repository.calculate(10.0, 3.0, "-")
        assertEquals("7", result)
    }

    @Test
    fun `subtraction resulting in negative`() {
        val result = repository.calculate(3.0, 10.0, "-")
        assertEquals("-7", result)
    }

    @Test
    fun `subtraction with zero`() {
        val result = repository.calculate(5.0, 0.0, "-")
        assertEquals("5", result)
    }

    // ========== Тестове за умножение ==========

    @Test
    fun `multiplication of two positive numbers`() {
        val result = repository.calculate(4.0, 5.0, "×")
        assertEquals("20", result)
    }

    @Test
    fun `multiplication with zero`() {
        val result = repository.calculate(100.0, 0.0, "×")
        assertEquals("0", result)
    }

    @Test
    fun `multiplication with negative number`() {
        val result = repository.calculate(4.0, -5.0, "×")
        assertEquals("-20", result)
    }

    @Test
    fun `multiplication of two negative numbers`() {
        val result = repository.calculate(-4.0, -5.0, "×")
        assertEquals("20", result)
    }

    // ========== Тестове за делене ==========

    @Test
    fun `division of two positive numbers`() {
        val result = repository.calculate(20.0, 4.0, "÷")
        assertEquals("5", result)
    }

    @Test
    fun `division by zero returns error`() {
        val result = repository.calculate(10.0, 0.0, "÷")
        assertEquals("Грешка", result)
    }

    @Test
    fun `division with decimal result`() {
        val result = repository.calculate(10.0, 4.0, "÷")
        assertEquals("2.5", result)
    }

    @Test
    fun `division of zero by number`() {
        val result = repository.calculate(0.0, 5.0, "÷")
        assertEquals("0", result)
    }

    // ========== Тестове за процент ==========

    @Test
    fun `percentage calculation`() {
        val result = repository.calculate(200.0, 10.0, "%")
        assertEquals("20", result)
    }

    @Test
    fun `percentage with zero`() {
        val result = repository.calculate(100.0, 0.0, "%")
        assertEquals("0", result)
    }

    // ========== Тестове за невалидни оператори ==========

    @Test
    fun `invalid operator returns error`() {
        val result = repository.calculate(5.0, 3.0, "^")
        assertEquals("Грешка", result)
    }

    @Test
    fun `empty operator returns error`() {
        val result = repository.calculate(5.0, 3.0, "")
        assertEquals("Грешка", result)
    }
}
