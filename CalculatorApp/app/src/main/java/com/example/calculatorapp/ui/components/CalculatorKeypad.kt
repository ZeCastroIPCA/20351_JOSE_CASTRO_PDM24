package com.example.calculatorapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorKeypad(
    onButtonClick: (String) -> Unit
) {
    // Layout of the calculator keypad
    val buttons = listOf(
        listOf("MRC", "M-", "M+", "ON/C"),
        listOf("7", "8", "9", "÷"),
        listOf("4", "5", "6", "×"),
        listOf("1", "2", "3", "-"),
        listOf("0", ".", "=", "+")
    )

    Column(modifier = Modifier.padding(top = 8.dp)) {
        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { label ->
                    // Set button color based on label
                    val buttonColor = when (label) {
                        "ON/C" -> Color(0xFFFF6B6B)
                        "=" -> Color(0xFFFFA726)
                        in listOf("÷", "×", "-", "+") -> Color(0xFFD3D3D3)
                        else -> Color(0xFF424242)
                    }
                    // Each button
                    CalculatorButton(
                        text = label,
                        modifier = Modifier.weight(1f).height(60.dp),
                        buttonColor = buttonColor,
                        onClick = { onButtonClick(label) }
                    )
                }
            }
        }
    }
}
