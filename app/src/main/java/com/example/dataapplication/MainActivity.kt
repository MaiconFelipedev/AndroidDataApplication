package com.example.dataapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Preview(showBackground = true)
@Composable
fun DiaDaSemana(modifier: Modifier = Modifier){
    val hoje = Calendario().diaDaSemana();
    val data1 = "24/03/2024"
    Column {
        Text(
            text = "Maicon Felipe da Silva Souza",
            modifier = modifier
        )
        Row {
            Text(
                text = "Dia: $hoje",
                modifier = modifier,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Dia da semana para $data1: ${Calendario().diaDaSemanaAlternativo(24, 3, 2024)}",
                modifier = modifier,
                color = Color.Blue
            )
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