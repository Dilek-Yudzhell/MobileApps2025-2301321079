package com.example.calculator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.databinding.ItemHistoryBinding
import com.example.calculator.model.CalculationHistory

/**
 * Adapter за RecyclerView с историята
 */
class HistoryAdapter : ListAdapter<CalculationHistory, HistoryAdapter.HistoryViewHolder>(
    HistoryDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * ViewHolder за елемент от историята
     */
    class HistoryViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CalculationHistory) {
            binding.tvExpression.text = item.expression
            binding.tvResult.text = "= ${item.result}"
            binding.tvTimestamp.text = item.getFormattedTime()
        }
    }

    /**
     * DiffCallback за ефективно обновяване на списъка
     */
    class HistoryDiffCallback : DiffUtil.ItemCallback<CalculationHistory>() {
        override fun areItemsTheSame(oldItem: CalculationHistory, newItem: CalculationHistory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CalculationHistory, newItem: CalculationHistory): Boolean {
            return oldItem == newItem
        }
    }
}
