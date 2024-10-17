package com.example.calculatorapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorDisplay(display: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFFE0E0E0))
            .padding(8.dp)
    ) {
        Text(
            text = display,
            // using sp because it adjusts in context with the user font's size settings
            fontSize = 36.sp,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterEnd)
        )
    }
}