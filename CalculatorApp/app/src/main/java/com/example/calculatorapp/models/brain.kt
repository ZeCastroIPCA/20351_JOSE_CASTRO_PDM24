package com.example.calculatorapp.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun CalculatorBrain() {
    var display by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf("") }
    var newNumber by remember { mutableStateOf(true) }

     fun numberButtonClick(number: String) {
         if (newNumber) display = number else display += number; newNumber = false
     }

    fun zeroButtonClick() {
        if (display != "0") if (newNumber) display = "0" else display += "0"
    }

    fun operationButtonClick(signal: String) {
        operation = signal
        firstNumber = display
        newNumber = true
    }

    fun dotButtonClick() {
        if (!display.contains(".")) display += "."
    }

    fun solveButtonClick() {
        // check if operation has a value
        // then do the operation
        // val == const in js
        val result = when (operation) {
            "+" -> firstNumber.toFloat() + display.toFloat()
            "-" -> firstNumber.toFloat() - display.toFloat()
            "×" -> firstNumber.toFloat() * display.toFloat()
            "÷" -> firstNumber.toFloat() / display.toFloat()
            else -> display.toFloat()
        }
        // check if number is decimal
        display = if (result % 1 == 0f) result.toInt().toString() else result.toString()
        newNumber = true
    }
}