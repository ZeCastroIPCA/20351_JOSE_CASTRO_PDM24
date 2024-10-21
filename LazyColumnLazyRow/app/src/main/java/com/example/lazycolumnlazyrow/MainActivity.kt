package com.example.lazycolumnlazyrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumnlazyrow.ui.theme.LazyColumnLazyRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnLazyRowTheme {
                val formLines = listOf(
                    FormLine("Nome", "text", "Digite seu nome"),
                    FormLine("Email", "email", "Digite seu email"),
                    FormLine("Idade", "number", "Digite sua idade"),
                    FormLine("Observações", "text", "Digite suas observações")
                )
                FormScreen(formLines)
            }
        }
    }
}

data class FormLine(
    val name: String,
    val type: String,
    val hint: String
)

@Composable
fun FormScreen(formLines: List<FormLine>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(formLines) { formLine ->
            FormLineItem(formLine)
        }
    }
}

@Composable
fun FormLineItem(formLine: FormLine) {
    var text by remember { mutableStateOf("") }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = formLine.name,
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(formLine.hint) },
            modifier = Modifier.weight(2f)
        )
    }
}