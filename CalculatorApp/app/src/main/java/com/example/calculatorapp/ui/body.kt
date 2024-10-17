package com.example.calculatorapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorBody() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF808080))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CalculatorDisplay(display = "0")
        CalculatorKeypad(display = "0", newNumber = true, operation = "+", firstNumber = "1")
    }
}