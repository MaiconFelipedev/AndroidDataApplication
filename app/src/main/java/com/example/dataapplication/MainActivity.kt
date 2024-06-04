package com.example.dataapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dataapplication.ui.theme.DataApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar

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

@Composable
fun CustomText() {
    val context = LocalContext.current
    val isDarkTheme = context.resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK == android.content.res.Configuration.UI_MODE_NIGHT_YES
    val textColor = if (isDarkTheme) Color.White else Color.Black
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DiaDaSemana(modifier: Modifier = Modifier){
    CustomText();

    val context = LocalContext.current

    // Definição do estado para tempo formatado
    var tempoFormatado by remember { mutableStateOf(Calendario().formatoHoras(Calendar.getInstance().time)) }

    // Função de atualização do tempo
    AtualizarHora { novoTempo ->
        tempoFormatado = novoTempo
    }

    val hoje = Calendario().diaDaSemana();
    val dataHoje = Calendario().dataAtual();
    // val tempoFormatado = Calendario().formatoHoras(Calendar.getInstance().time);

    var dia by remember { mutableStateOf(TextFieldValue("")) }
    var mes by remember { mutableStateOf(TextFieldValue("")) }
    var ano by remember { mutableStateOf(TextFieldValue("")) }
    var diaDaSemana by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Maicon Felipe da Silva Souza",
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$tempoFormatado",
            style = androidx.compose.ui.text.TextStyle(fontSize = 40.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$hoje | $dataHoje",
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
        )
    }
}

@Composable
fun AtualizarHora(onTimeUpdate: (String) -> Unit) {
    // Use a rememberCoroutineScope to launch a coroutine for updating time
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val job = coroutineScope.launch {
            while (isActive) {
                val currentTime = Calendar.getInstance().time
                val formattedTime = Calendario().formatoHoras(currentTime)
                onTimeUpdate(formattedTime)
                delay(1000L)
            }
        }

        onDispose {
            job.cancel()
        }
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