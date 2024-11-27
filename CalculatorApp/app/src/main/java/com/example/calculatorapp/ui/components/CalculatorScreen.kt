package com.example.calculatorapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CalculatorScreen(
    display: String, // Current display value
    onButtonClick: (String) -> Unit // Callback for button clicks
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF808080))
            .padding(16.dp)
    ) {
        // Display area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFFE0E0E0))
                .padding(8.dp)
        ) {
            Text(
                text = display,
                fontSize = 36.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterEnd)
            )
        }
        // Keypad
        CalculatorKeypad(
            onButtonClick = onButtonClick
        )
    }
}
