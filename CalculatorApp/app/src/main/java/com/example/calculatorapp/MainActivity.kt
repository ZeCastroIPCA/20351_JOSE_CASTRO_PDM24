package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                Calculator()
            }
        }
    }
}

@Composable
fun Calculator() {
    var display by remember { mutableStateOf("0") }
    var operation by remember { mutableStateOf("") }
    var firstNumber by remember { mutableStateOf("") }
    var newNumber by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF808080))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Display
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
            CalculatorButton("7") { if (newNumber) display = "7" else display += "7"; newNumber = false }
            CalculatorButton("8") { if (newNumber) display = "8" else display += "8"; newNumber = false }
            CalculatorButton("9") { if (newNumber) display = "9" else display += "9"; newNumber = false }
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
            CalculatorButton("4") { if (newNumber) display = "4" else display += "4"; newNumber = false }
            CalculatorButton("5") { if (newNumber) display = "5" else display += "5"; newNumber = false }
            CalculatorButton("6") { if (newNumber) display = "6" else display += "6"; newNumber = false }
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
            // check if number is the first number
            // if not, display it
            // if so, concatenate the number to the previous
            // this applies for all number
            CalculatorButton("1") { if (newNumber) display = "1" else display += "1"; newNumber = false }
            CalculatorButton("2") { if (newNumber) display = "2" else display += "2"; newNumber = false }
            CalculatorButton("3") { if (newNumber) display = "3" else display += "3"; newNumber = false }
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
            CalculatorButton("0") { if (display != "0") if (newNumber) display = "0" else display += "0" }
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

@Composable
fun CalculatorButton(
    text: String,
    buttonColor: Color = Color(0xFF424242),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
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

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorAppTheme {
        Calculator()
    }
}