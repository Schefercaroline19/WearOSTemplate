package com.ifpr.wearostemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.google.firebase.database.FirebaseDatabase
import com.ifpr.wearostemplate.Corrida
import com.ifpr.wearostemplate.R
import com.ifpr.wearostemplate.TelaDois
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("home") }
            val context = LocalContext.current

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                TimeText()

                if (currentScreen == "home") {

                    Button(
                        onClick = {
                            val intent = Intent(context, TelaDois::class.java)
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 22.dp, end = 22.dp)
                            .size(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.DarkGray,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "👤",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }

                    RunningScreen(onPlayClick = { currentScreen = "detalhes" })

                } else if (currentScreen == "detalhes") {
                    DetailScreen(onStopClick = {
                        salvarCorridaNoFirebase()
                        currentScreen = "home"
                    })
                }
            }
        }
    }

    private fun salvarCorridaNoFirebase() {
        val database = FirebaseDatabase.getInstance().getReference("corridas")
        val corridaId = database.push().key ?: return

        val dataAtual = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())

        val novaCorrida = Corrida(
            distanciaKm = 5.2,
            tempoSegundos = 1500,
            ritmoMedio = "5'12\"/km",
            dataHora = dataAtual
        )

        database.child(corridaId).setValue(novaCorrida)
            .addOnSuccessListener {
                Toast.makeText(this, "Corrida salva no Firebase!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Erro: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

@Composable
fun RunningScreen(onPlayClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_run),
            contentDescription = "Ícone de Corrida",
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Iniciar corrida",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(12.dp))

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
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DetailScreen(onStopClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Em Corrida...",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Stop que salva e volta
        Button(
            onClick = onStopClick,
            modifier = Modifier.size(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "⏹",
                fontSize = 22.sp,
                color = Color.White
            )
        }
    }
}