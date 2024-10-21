package com.example.calculatorapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculatorapp.models.CalculatorBrain

class viewModel : ViewModel() {
    val brain = mutableStateOf(CalculatorBrain())
}