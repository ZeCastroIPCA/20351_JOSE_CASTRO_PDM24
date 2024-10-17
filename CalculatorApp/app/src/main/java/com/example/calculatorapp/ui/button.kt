package com.example.calculatorapp.ui

import androidx.compose.foundation.layout.height
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
    text: String,
    buttonColor: Color = Color(0xFF424242),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        // get the color of the button from the buttonColor parameter
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .height(60.dp)

    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}