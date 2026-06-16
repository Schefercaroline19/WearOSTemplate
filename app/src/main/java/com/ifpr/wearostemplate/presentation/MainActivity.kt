package com.ifpr.wearostemplate.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var currentScreen by remember { mutableStateOf("home") }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                // Relógio
                TimeText()

                // Alterna entre a tela inicial e a nova tela
                if (currentScreen == "home") {
                    RunningScreen(onPlayClick = { currentScreen = "detalhes" })
                } else {
                    DetailScreen()
                }
            }
        }
    }
}

@Composable
fun RunningScreen(onPlayClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        androidx.compose.foundation.Image(
            painter = androidx.compose.ui.res.painterResource(id = com.ifpr.wearostemplate.R.drawable.ic_run),
            contentDescription = "Ícone de Corrida",
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Iniciar corrida",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = onPlayClick,
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ),
            border = ButtonDefaults.outlinedButtonBorder(
                borderColor = Color.White,
                borderWidth = 2.dp
            )
        ) {
            Text(
                text = "▶",
                fontSize = 26.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DetailScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "outra tela",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        )
    }
}