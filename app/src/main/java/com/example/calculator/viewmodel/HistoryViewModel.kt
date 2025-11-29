package com.example.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.model.CalculationHistory
import com.example.calculator.repository.CalculatorRepository

/**
 * ViewModel за екрана с историята
 */
class HistoryViewModel : ViewModel() {

    private val repository = CalculatorRepository()

    private val _historyList = MutableLiveData<List<CalculationHistory>>()
    val historyList: LiveData<List<CalculationHistory>> = _historyList

    private val _isEmpty = MutableLiveData(true)
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        loadHistory()
    }

    /**
     * Зарежда историята
     */
    fun loadHistory() {
        val history = repository.getHistory()
        _historyList.value = history
        _isEmpty.value = history.isEmpty()
    }

    /**
     * Изчиства историята
     */
    fun clearHistory() {
        repository.clearHistory()
        loadHistory()
    }
}
