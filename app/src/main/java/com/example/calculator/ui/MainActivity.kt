package com.example.calculator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.model.Operation
import com.example.calculator.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupButtons()
    }

    private fun setupObservers() {
        viewModel.displayText.observe(this) {
            binding.tvResult.text = it
        }
        viewModel.expressionText.observe(this) {
            binding.tvExpression.text = it
        }
    }

    private fun setupButtons() {
        val num = listOf(
            binding.btn0 to "0", binding.btn1 to "1", binding.btn2 to "2",
            binding.btn3 to "3", binding.btn4 to "4", binding.btn5 to "5",
            binding.btn6 to "6", binding.btn7 to "7", binding.btn8 to "8", binding.btn9 to "9"
        )
        num.forEach { (button, value) ->
            button.setOnClickListener { viewModel.onNumberClick(value) }
        }

        binding.btnDot.setOnClickListener { viewModel.onDecimalClick() }
        binding.btnPlusMinus.setOnClickListener { viewModel.onPlusMinusClick() }

        binding.btnAdd.setOnClickListener { viewModel.onOperationClick(Operation.ADD) }
        binding.btnSubtract.setOnClickListener { viewModel.onOperationClick(Operation.SUBTRACT) }
        binding.btnMultiply.setOnClickListener { viewModel.onOperationClick(Operation.MULTIPLY) }
        binding.btnDivide.setOnClickListener { viewModel.onOperationClick(Operation.DIVIDE) }

        binding.btnEquals.setOnClickListener { viewModel.onEqualsClick() }

        binding.btnClear.setOnClickListener { viewModel.clear() }
        binding.btnPercent.setOnClickListener { viewModel.onPercentClick() }
        binding.btnBrackets.setOnClickListener { viewModel.onBracketsClick() }
    }
}

