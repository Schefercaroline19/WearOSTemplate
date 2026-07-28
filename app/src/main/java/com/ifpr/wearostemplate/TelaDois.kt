package com.ifpr.wearostemplate

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

class TelaDois : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                // Mantém o relógio visível no topo do smartwatch
                TimeText()

                // Chama a tela passando a ação de fechar ao clicar no botão de voltar
                ProfileScreen(onBackClick = {
                    finish()
                })
            }
        }
    }
}

@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Emoji ou ícone de perfil no topo
        Text(
            text = "👤",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Nome do Usuário
        Text(
            text = "Nome: Usuário",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Dados de monitorização comuns para atividades físicas
        Text(
            text = "Idade: 25 anos",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "Peso: 75 kg",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Botão circular estilizado para voltar à MainActivity
        Button(
            onClick = onBackClick,
            modifier = Modifier.size(38.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "↩",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}