package com.example.dataapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dataapplication.ui.theme.DataApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiaDaSemana();
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DiaDaSemana(modifier: Modifier = Modifier){
    val hoje = Calendario().diaDaSemana();
    val dataHoje = Calendario().dataAtual();
    val data1 = "03/06/2024"

    var dia by remember { mutableStateOf(TextFieldValue("")) }
    var mes by remember { mutableStateOf(TextFieldValue("")) }
    var ano by remember { mutableStateOf(TextFieldValue("")) }
    var diaDaSemana by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Maicon Felipe da Silva Souza",
            color = Color.Yellow
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Dia: $hoje, Data: $dataHoje",
            color = Color.Yellow
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = dia,
            onValueChange = { newValue -> dia = newValue },
            label = { Text("Dia") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = mes,
            onValueChange = { newValue -> mes = newValue },
            label = { Text("Mês") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = ano,
            onValueChange = { newValue -> ano = newValue },
            label = { Text("Ano") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val diaInt = dia.text.toIntOrNull() ?: 0
            val mesInt = mes.text.toIntOrNull() ?: 0
            val anoInt = ano.text.toIntOrNull() ?: 0
            diaDaSemana = if (diaInt in 1..31 && mesInt in 1..12 && anoInt > 0) {
                Calendario().diaDaSemanaAlternativo(diaInt, mesInt, anoInt)
            } else {
                "Data inválida"
            }
        }) {
            Text("Calcular Dia da Semana")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Dia da semana para a data informada: $diaDaSemana",
            color = Color.Green

        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    DataApplicationTheme {
        Greeting("Android")
    }
}