package com.example.calculator.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculator.R
import com.example.calculator.databinding.ActivityHistoryBinding
import com.example.calculator.ui.adapter.HistoryAdapter
import com.example.calculator.viewmodel.HistoryViewModel

/**
 * Activity за показване на историята на изчисленията
 */
class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupObservers()
    }

    /**
     * Настройка на toolbar с back бутон
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.history_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    /**
     * Настройка на RecyclerView
     */
    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = historyAdapter
            addItemDecoration(
                DividerItemDecoration(this@HistoryActivity, DividerItemDecoration.VERTICAL)
            )
        }
    }

    /**
     * Настройка на observers
     */
    private fun setupObservers() {
        viewModel.historyList.observe(this) { history ->
            historyAdapter.submitList(history)
        }

        viewModel.isEmpty.observe(this) { isEmpty ->
            binding.layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_clear_history -> {
                showClearHistoryDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Показва диалог за изчистване на историята
     */
    private fun showClearHistoryDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.clear_history)
            .setMessage(R.string.clear_history_confirm)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.clearHistory()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadHistory()
    }
}
