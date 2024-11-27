package com.example.calculatorapp.models

class CalculatorBrain {
    fun calculateResult(firstNumber: String, secondNumber: String, operation: String): String {
        // Function to calculate the result based on the operation
        val result = when (operation) {
            "+" -> firstNumber.toFloat() + secondNumber.toFloat() // Add
            "-" -> firstNumber.toFloat() - secondNumber.toFloat() // Subtract
            "×" -> firstNumber.toFloat() * secondNumber.toFloat() // Multiply
            "÷" -> if (secondNumber.toFloat() != 0f) firstNumber.toFloat() / secondNumber.toFloat() else Float.NaN // Divide
            else -> {
                return secondNumber // Return the second number if the operation is invalid
            }
        }
        // Return the result as a string with no decimal point if the result is an integer
        return if (result % 1 == 0f) result.toInt().toString() else result.toString()
    }
}
