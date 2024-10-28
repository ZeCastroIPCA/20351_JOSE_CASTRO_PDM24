package com.example.car_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.car_list.ui.theme.Car_listTheme
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Car_listTheme {
                CarApp()
            }
        }
    }
}

data class Car(
    val name: String,
    val description: String
)

@Composable
fun CarListScreen(
    navController: NavController,
    carList: MutableState<List<Car>> = remember { mutableStateOf(listOf()) }
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("add_car")
            }) {
                Icon(Icons.Default.Add, "Adicionar carro")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(carList.value) { car ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("car_details/${car.name}/${car.description}")
                        }
                ) {
                    Text(
                        text = car.name,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CarDetailsScreen(
    car: Car,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = car.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = car.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AddCarScreen(
    onCarAdded: (Car) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome do carro") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                if (name.isNotBlank() && description.isNotBlank()) {
                    onCarAdded(Car(name, description))
                    onBack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Adicionar")
        }
    }
}

@Composable
fun CarApp() {
    val navController = rememberNavController()
    val carList = remember { mutableStateOf(listOf<Car>()) }

    NavHost(navController = navController, startDestination = "car_list") {
        composable("car_list") {
            CarListScreen(navController, carList)
        }

        composable(
            "car_details/{name}/{description}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            CarDetailsScreen(
                car = Car(name, description),
                onBack = { navController.popBackStack() }
            )
        }

        composable("add_car") {
            AddCarScreen(
                onCarAdded = { car ->
                    carList.value = carList.value + car
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}