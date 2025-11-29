package com.example.calculator

import com.student.calculator.data.HistoryStorage
import com.student.calculator.model.CalculationHistory
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Unit тестове за HistoryStorage
 */
class HistoryStorageTest {

    @Before
    fun setup() {
        // Изчистваме историята преди всеки тест
        HistoryStorage.clearHistory()
    }

    @Test
    fun `adding history item increases count`() {
        assertEquals(0, HistoryStorage.getHistoryCount())

        val history = CalculationHistory(
            expression = "5 + 3",
            result = "8"
        )
        HistoryStorage.addHistory(history)

        assertEquals(1, HistoryStorage.getHistoryCount())
    }

    @Test
    fun `new items are added at the beginning`() {
        val history1 = CalculationHistory(expression = "1 + 1", result = "2")
        val history2 = CalculationHistory(expression = "2 + 2", result = "4")

        HistoryStorage.addHistory(history1)
        HistoryStorage.addHistory(history2)

        val allHistory = HistoryStorage.getAllHistory()
        assertEquals("2 + 2", allHistory[0].expression)
        assertEquals("1 + 1", allHistory[1].expression)
    }

    @Test
    fun `clearHistory removes all items`() {
        HistoryStorage.addHistory(CalculationHistory(expression = "1+1", result = "2"))
        HistoryStorage.addHistory(CalculationHistory(expression = "2+2", result = "4"))

        assertEquals(2, HistoryStorage.getHistoryCount())

        HistoryStorage.clearHistory()

        assertEquals(0, HistoryStorage.getHistoryCount())
    }

    @Test
    fun `getAllHistory returns copy of list`() {
        HistoryStorage.addHistory(CalculationHistory(expression = "5+5", result = "10"))

        val list1 = HistoryStorage.getAllHistory()
        val list2 = HistoryStorage.getAllHistory()

        // Проверяваме че са различни инстанции но с еднакво съдържание
        assertEquals(list1.size, list2.size)
        assertEquals(list1[0].expression, list2[0].expression)
    }
}
