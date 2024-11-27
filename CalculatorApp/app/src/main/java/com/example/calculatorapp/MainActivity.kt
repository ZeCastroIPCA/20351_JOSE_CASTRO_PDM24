package com.example.calculatorapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.ui.components.*
import com.example.calculatorapp.viewmodels.CalculatorViewModel
import com.example.calculatorapp.ui.themes.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                val viewModel = CalculatorViewModel()// Creating an instance of the ViewModel
                // Setting up the calculator UI and linking it with the ViewModel
                CalculatorBody(
                    viewModel = viewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorAppTheme {
        CalculatorBody(
            viewModel = CalculatorViewModel() // Using a mock ViewModel for preview
        )
    }
}