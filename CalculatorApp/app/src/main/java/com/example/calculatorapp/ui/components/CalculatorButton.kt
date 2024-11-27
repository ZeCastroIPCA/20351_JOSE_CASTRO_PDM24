package com.example.calculatorapp.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    text: String, // Button label
    modifier: Modifier = Modifier, // Modifier for the button
    buttonColor: Color = Color(0xFF424242), // Button color
    onClick: () -> Unit // Callback for button click
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}
