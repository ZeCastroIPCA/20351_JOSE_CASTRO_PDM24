package com.example.calculatorapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorKeypad(display: String, newNumber: Boolean, operation: String, firstNumber: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("MRC", onClick = { /* TODO */ })
            CalculatorButton("M-", onClick = { /* TODO */ })
            CalculatorButton("M+", onClick = { /* TODO */ })
            CalculatorButton("ON/C", Color(0xFFFF6B6B)) { display = "0" }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // check if number is the first number
            // if not, display it
            // if so, concatenate the number to the previous
            // this applies for all number
            CalculatorButton("7") {
                if (newNumber) display = "7" else display += "7"; newNumber = false
            }
            CalculatorButton("8") {
                if (newNumber) display = "8" else display += "8"; newNumber = false
            }
            CalculatorButton("9") {
                if (newNumber) display = "9" else display += "9"; newNumber = false
            }
            CalculatorButton("÷") {
                operation = "÷"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("4") {
                if (newNumber) display = "4" else display += "4"; newNumber = false
            }
            CalculatorButton("5") {
                if (newNumber) display = "5" else display += "5"; newNumber = false
            }
            CalculatorButton("6") {
                if (newNumber) display = "6" else display += "6"; newNumber = false
            }
            CalculatorButton("×") {
                operation = "×"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("1") {
                if (newNumber) display = "1" else display += "1"; newNumber = false
            }
            CalculatorButton("2") {
                if (newNumber) display = "2" else display += "2"; newNumber = false
            }
            CalculatorButton("3") {
                if (newNumber) display = "3" else display += "3"; newNumber = false
            }
            CalculatorButton("-") {
                operation = "-"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("0") {
                if (display != "0") if (newNumber) display = "0" else display += "0"
            }
            CalculatorButton(".") { if (!display.contains(".")) display += "." }
            CalculatorButton("=") {
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
            CalculatorButton("+") {
                operation = "+"
                firstNumber = display
                newNumber = true
            }
        }
    }
}