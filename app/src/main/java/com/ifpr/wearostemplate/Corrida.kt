package com.ifpr.wearostemplate

data class Corrida(
    val distanciaKm: Double = 0.0,
    val tempoSegundos: Long = 0,
    val ritmoMedio: String = "",
    val dataHora: String = ""
)