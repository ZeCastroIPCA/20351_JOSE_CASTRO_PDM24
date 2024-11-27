package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.themes.CalculatorAppTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.height

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
            CalculatorButton("MRC", modifier = Modifier.weight(1f).height(60.dp), onClick = { /* TODO */ })
            CalculatorButton("M-", modifier = Modifier.weight(1f).height(60.dp), onClick = { /* TODO */ })
            CalculatorButton("M+", modifier = Modifier.weight(1f).height(60.dp), onClick = { /* TODO */ })
            CalculatorButton("ON/C", modifier = Modifier.weight(1.5f).height(60.dp), Color(0xFFFF6B6B)) { display = "0" }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // check if number is the first number
            // if not, display it
            // if so, concatenate the number to the previous
            // this applies for all number
            CalculatorButton("7", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "7" else display += "7"; newNumber = false }
            CalculatorButton("8", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "8" else display += "8"; newNumber = false }
            CalculatorButton("9", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "9" else display += "9"; newNumber = false }
            CalculatorButton("÷", modifier = Modifier.weight(1.5f).height(60.dp), Color(0xFFD3D3D3)) {
                operation = "÷"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("4", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "4" else display += "4"; newNumber = false }
            CalculatorButton("5", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "5" else display += "5"; newNumber = false }
            CalculatorButton("6", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "6" else display += "6"; newNumber = false }
            CalculatorButton("×", modifier = Modifier.weight(1.5f).height(60.dp), Color(0xFFD3D3D3)) {
                operation = "×"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("1", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "1" else display += "1"; newNumber = false }
            CalculatorButton("2", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "2" else display += "2"; newNumber = false }
            CalculatorButton("3", modifier = Modifier.weight(1f).height(60.dp)) { if (newNumber) display = "3" else display += "3"; newNumber = false }
            CalculatorButton("-", modifier = Modifier.weight(1.5f).height(60.dp), Color(0xFFD3D3D3)) {
                operation = "-"
                firstNumber = display
                newNumber = true
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CalculatorButton("0", modifier = Modifier.weight(1f).height(60.dp)) { if (display != "0") if (newNumber) display = "0" else display += "0" }
            CalculatorButton(".", modifier = Modifier.weight(1f).height(60.dp)) { if (!display.contains(".")) display += "." }
            CalculatorButton("=", modifier = Modifier.weight(1f).height(60.dp), Color(0xFFFFA726)) {
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
            CalculatorButton("+", modifier = Modifier.weight(1.5f).height(60.dp), Color(0xFFD3D3D3)) {
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
    modifier: Modifier = Modifier,
    buttonColor: Color = Color(0xFF424242),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        // get the color of the button from the buttonColor parameter
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

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorAppTheme {
        Calculator()
    }
}