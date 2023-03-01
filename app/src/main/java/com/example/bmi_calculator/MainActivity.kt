package com.example.bmi_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmi_calculator.ui.theme.BMI_calculatorTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMI_calculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   BMICalculatorScreen()

                   }
                }
            }
        }
    }


class BmiViewModel : ViewModel() {
    // Height in meters
    val height = mutableStateOf(1.7f)

    // Weight in kilograms
    val weight = mutableStateOf(70f)

    fun calculateBMI(): Float {
        return weight.value / (height.value * height.value)
    }
}

@Composable
fun BMICalculatorScreen () {
    Column(
        modifier = Modifier.fillMaxSize()) {
        val viewModel: BmiViewModel = viewModel()


        TextField(
            value = viewModel.height.value.toString(),
            onValueChange = { value -> viewModel.height.value = value.toFloatOrNull() ?: 0f },
            label = { Text(text = "Height (m)") }
        )

        TextField(
            value = viewModel.weight.value.toString(),
            onValueChange = { value -> viewModel.weight.value = value.toFloatOrNull() ?: 0f },
            label = { Text(text = "Weight (kg)") }
        )

        Text(
            text = "BMI: ${viewModel.calculateBMI()}",
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Button(onClick = {}) {
            Text(text = "Calculate BMI")
        }
    }
}

