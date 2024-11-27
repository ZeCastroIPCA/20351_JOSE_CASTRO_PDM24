package com.example.calculatorapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculatorapp.models.CalculatorBrain

class CalculatorViewModel : ViewModel() {
    var display by mutableStateOf("0") // Display value
    private var operation by mutableStateOf("") // Operation to perform
    private var firstNumber by mutableStateOf("") // First number
    private var newNumber by mutableStateOf(true) // Flag to check if a new number is being entered
    private val calculatorBrain = CalculatorBrain() // CalculatorBrain instance

    // Function to handle button clicks
    fun onButtonClick(label: String) {
        when (label) {
            // Clear the display
            "ON/C" -> {
                display = "0"
                newNumber = true
            }
            // Get the result of the operation
            "=" -> {
                val result = calculatorBrain.calculateResult(firstNumber, display, operation)
                display = result
                newNumber = true
            }
            // Perform the operation (+, -, ×, ÷)
            "+" -> {
                operation = "+"
                firstNumber = display
                newNumber = true
            }
            "-" -> {
                operation = "-"
                firstNumber = display
                newNumber = true
            }
            "×" -> {
                operation = "×"
                firstNumber = display
                newNumber = true
            }
            "÷" -> {
                operation = "÷"
                firstNumber = display
                newNumber = true
            }
            // Append the number to the display
            else -> {
                if (newNumber) display = label else display += label
                newNumber = false
            }
        }
    }
}
