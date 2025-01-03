package com.example.storeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.storeapp.domain.enums.Roles
import com.example.storeapp.navigation.Screen
import com.example.storeapp.viewmodel.AuthViewModel
import com.example.storeapp.viewmodel.UserViewModel


@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    userViewModel: UserViewModel,
    onLogout: () -> Unit
) {

    val currentUser by userViewModel.currentUser.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.getCurrentUser()
    }

    var cartCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Configurações",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Column (
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = "Informações do Utilizador",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = currentUser?.name ?: "Loading...")
                Text(text = currentUser?.email ?: "Loading...")
                if (currentUser?.role == Roles.ADMIN.name) {
                    Text(text = currentUser?.role ?: "Loading...")
                }
            }

            if (currentUser?.role == Roles.ADMIN.name) {
                Column (
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text(
                        text = "Menú de Produtos",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                    )
                    Button(
                        onClick = {
                            navController.navigate(Screen.Products.route)
                        },
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, top = 8.dp)
                            .height(50.dp)
                    ) {
                        Text(text = "Ver Produtos", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Column (
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = "Encontrar Carrinho",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextField(
                    value = cartCode,
                    onValueChange = {
                        cartCode = it
                    },
                    label = { Text("Código do Carrinho") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Button(
                    onClick = {
                        navController.navigate(Screen.Cart.route + "/$cartCode")
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 8.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Ver Carrinho", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }

        Button(
            onClick = {
                authViewModel.logout()
                onLogout()
            },
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 13.dp, end = 13.dp)
                .height(50.dp)
        ) {
            Text(text = "Encerrar Sessão", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
