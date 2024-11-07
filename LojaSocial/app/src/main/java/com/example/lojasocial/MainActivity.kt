package com.example.lojasocial

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lojasocial.ui.theme.LojaSocialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LojaSocialTheme {
                Login(modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
fun Login(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Login Logo", modifier.size(64.dp))
        Spacer(modifier.size(32.dp))
        Text(text = "Iniciar Sessão", fontSize = 32.sp)
        Spacer(modifier.size(32.dp))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                username,
                onValueChange = {
                    text -> username = text
                },
                placeholder = {
                Text(text = "Utilizador")
                }
            )
            Spacer(modifier.size(16.dp))
            OutlinedTextField(
                password,
                onValueChange = {
                        text -> password = text
                },
                placeholder = {
                    Text(text = "********")
                }
            )
            Spacer(modifier.size(32.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor =
                if(username.isNotBlank() && password.isNotBlank()) {
                    Color.LightGray
                } else {
                    Color.Gray
                })
            ) {
                Text(text = "Entrar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = if(username.isNotBlank() && password.isNotBlank()) {
                        Color.White
                    } else {
                        Color.LightGray
                    })
            }
            Spacer(modifier.size(128.dp))
        }
    }
}