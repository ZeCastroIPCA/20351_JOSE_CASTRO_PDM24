package com.example.calculatorapp.ui.components

import androidx.compose.runtime.Composable
import com.example.calculatorapp.viewmodels.CalculatorViewModel

@Composable
fun CalculatorBody(
    viewModel: CalculatorViewModel
) {
    // Passing the display value and button click handler from the ViewModel
    CalculatorScreen(
        display = viewModel.display, // Display value
        onButtonClick= { viewModel.onButtonClick(it) } // Button click handler
    )
}
